# Eorzea Places Application

Aplikasi Android berbasis **Jetpack Compose**, **Navigation Compose (Type-Safe Navigation)**, **Room 3 (Local Database)**, **Architecture Components (ViewModel, StateFlow & Repository Pattern)**, dan **Material 3** untuk menampilkan dan mengelola katalog tempat wisata/lokasi di dunia Eorzea (*Final Fantasy XIV*).

Aplikasi ini menerapkan arsitektur Android modern (**Data Layer + Repository Layer + UI Layer**) dengan prinsip *Unidirectional Data Flow* (UDF), persistensi data lokal SQLite melalui Room 3, serta pengelolaan state yang aman terhadap perubahan konfigurasi (*configuration changes*).

---

## 1. Tentang Aplikasi

**Eorzea Places** adalah aplikasi seluler yang dirancang untuk menampilkan berbagai lokasi dan tempat legendaris di Eorzea. Data disimpan secara persisten di dalam database SQLite lokal menggunakan Room 3 dan dikelola melalui `PlaceRepository` serta `PlaceViewModel`.

### Fitur Utama
* 🗺️ **Katalog Tempat (Grid View)**: Menampilkan lokasi-lokasi dalam tata letak kisi 2 kolom (`LazyVerticalGrid`) di `HomeScreen` secara reaktif dari database.
* 🔍 **Detail Tempat (Detail View)**: Menampilkan informasi lengkap lokasi (Gambar, Nama, Lokasi, Deskripsi) pada `DetailScreen`.
* ✏️ **Pengeditan tempat dengan Persistensi Data**: Pengguna dapat memperbarui Nama, Lokasi, dan Deskripsi tempat melalui `EditScreen`. Perubahan disimpan secara otomatis ke database Room.
* 🌱 **Database Auto-Seeding**: Aplikasi secara otomatis melakukan *initial data seeding* pada peluncuran pertama jika database dalam keadaan kosong.
* 🧭 **Type-Safe Navigation**: Menggunakan `@Serializable` objek/class rute (`Home`, `Detail(id: Int)`, `Edit(id: Int)`) untuk navigasi antar layar yang aman tipe data.
* ⚡ **ViewModel & StateFlow Architecture**: Menggunakan `PlaceViewModel` dan `EditViewModel` dengan enkapsulasi `MutableStateFlow` / `StateFlow` serta konsumsi state yang *lifecycle-aware* (`collectAsStateWithLifecycle()`).
* 🛡️ **Form Validation & Non-Happy Path Handling**: Memvalidasi input form secara otomatis (`isFormValid`), sehingga tombol *Save* hanya aktif jika semua field terisi dan valid.

---

## 2. Arsitektur & Struktur Kode

Struktur kode proyek terorganisasi ke dalam arsitektur berlapis di [app/src/main/java/com/example/myapplication](app/src/main/java/com/example/myapplication):

```
com.example.myapplication/
├── PlaceApplication.kt          # Custom Application Class (Singletons Database & Repository)
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   └── PlaceDao.kt      # Data Access Object (Room 3 DAO interface)
│   │   ├── database/
│   │   │   └── AppDatabase.kt   # Room Database Singleton (AndroidSQLiteDriver)
│   │   ├── entity/
│   │   │   └── PlaceEntity.kt   # Room Entity class (table: "place")
│   │   └── source/
│   │       └── PlaceData.kt     # Initial data seed (initialPlaces)
│   └── repository/
│       └── PlaceRepository.kt   # Repository Layer (Single Source of Truth)
├── navigation/
│   └── AppNavigation.kt         # Setup NavHost, Type-Safe Routes, & ViewModel Injection
└── ui/
    ├── App.kt                  # Container utama aplikasi (AppHeader + AppNavigation)
    ├── components/
    │   ├── AppHeader.kt        # Header judul aplikasi
    │   └── PlaceCard.kt        # Card item tempat di grid
    ├── home/
    │   └── HomeScreen.kt       # Layar Katalog Utama (Grid View)
    ├── detail/
    │   └── DetailScreen.kt     # Layar Detail Tempat
    ├── edit/                   # UI Layer: Edit Feature
    │   ├── EditRoute.kt        # Stateful Route: Menghubungkan ViewModel, State, & Callbacks
    │   ├── EditScreen.kt       # Stateless Screen: UI Murni (Menerima UiState & Callbacks)
    │   ├── EditUiState.kt      # Immutable State: State form & logika validasi (isFormValid)
    │   └── EditViewModel.kt    # Screen-Level State Holder untuk Form Edit
    └── viewmodel/
        └── PlaceViewModel.kt   # Screen-Level State Holder untuk Koleksi Tempat (StateIn Flow)
```

---

## 3. Penjelasan Layer Arsitektur

### A. Data Layer (Room 3 Database)
* **`PlaceEntity`** ([PlaceEntity.kt](app/src/main/java/com/example/myapplication/data/local/entity/PlaceEntity.kt))  
  Entity `@Entity(tableName = "place")` yang merepresentasikan tabel tempat di SQLite dengan kolom `id` (`PrimaryKey`), `name`, `location`, `imageId`, dan `description`.
* **`PlaceDao`** ([PlaceDao.kt](app/src/main/java/com/example/myapplication/data/local/dao/PlaceDao.kt))  
  Interface `@Dao` yang menyediakan query reaktif `observeAll(): Flow<List<PlaceEntity>>` serta operasi CRUD (`insert`, `insertAll`, `update`, `delete`, `count`).
* **`AppDatabase`** ([AppDatabase.kt](app/src/main/java/com/example/myapplication/data/local/database/AppDatabase.kt))  
  Kelas database Room 3 bertipe `@Database` yang menggunakan `AndroidSQLiteDriver()` untuk koneksi database SQLite lokal.

### B. Repository Layer
* **`PlaceRepository`** ([PlaceRepository.kt](app/src/main/java/com/example/myapplication/data/repository/PlaceRepository.kt))  
  Berfungsi sebagai *Single Source of Truth*. Mengabstraksikan akses data dari `PlaceDao`, mengekspos alur data reaktif `places: Flow<List<PlaceEntity>>`, dan menyediakan fungsi `seedIfEmpty()` untuk mengisikan data awal secara otomatis jika database belum terisi.

### C. Application Container
* **`PlaceApplication`** ([PlaceApplication.kt](app/src/main/java/com/example/myapplication/PlaceApplication.kt))  
  Kelas turunan `Application` yang menginisialisasi *instance* `AppDatabase` dan `PlaceRepository` secara *lazy* untuk digunakan secara global oleh seluruh aplikasi.

### D. UI & State Management Layer
* **`PlaceViewModel`** ([PlaceViewModel.kt](app/src/main/java/com/example/myapplication/ui/viewmodel/PlaceViewModel.kt))  
  State holder tingkat layar yang mengonversi `Flow` tempat dari repository menjadi `StateFlow<List<PlaceEntity>>` menggunakan `stateIn()`. Juga menjalankan `seedIfEmpty()` saat inisialisasi dan menangani aksi penambahan/pengubahan/penghapusan data.
* **`EditViewModel`** ([EditViewModel.kt](app/src/main/java/com/example/myapplication/ui/edit/EditViewModel.kt))  
  State holder khusus untuk layar edit yang mengelola form input menggunakan `MutableStateFlow` (`_uiState`) dan mengeksposnya via `asStateFlow()` (`uiState`).
* **`EditUiState`** ([EditUiState.kt](app/src/main/java/com/example/myapplication/ui/edit/EditUiState.kt))  
  Data class *immutable* yang menyimpan state input form (`name`, `location`, `description`) dan properti terhitung `isFormValid` untuk validasi form.
* **`EditRoute`** ([EditRoute.kt](app/src/main/java/com/example/myapplication/ui/edit/EditRoute.kt))  
  *Stateful Composable* yang menghubungkan `EditViewModel` dengan `EditScreen` menggunakan `collectAsStateWithLifecycle()`.
* **`EditScreen`** ([EditScreen.kt](app/src/main/java/com/example/myapplication/ui/edit/EditScreen.kt))  
  *Stateless Composable* murni yang hanya menerima state dan callback tanpa referensi `NavController`.

---

## 4. Cara Menjalankan

### Prasyarat
* **Android Studio**: Android Studio Ladybug atau versi yang lebih baru.
* **JDK**: Java Development Kit (JDK) 11 atau lebih baru.
* **Min SDK**: Level 24 (Android 7.0 Nougat) atau lebih baru.
* **Target / Compile SDK**: Level 35/37 (Android 15).

### Langkah-Langkah

1. **Buka Project**  
   Buka Android Studio, pilih **Open**, lalu arahkan ke direktori project ini.

2. **Sinkronisasi Gradle**  
   Tunggu hingga proses *Gradle Sync* selesai (atau pilih **File > Sync Project with Gradle Files**).

3. **Jalankan Aplikasi**  
   * Hubungkan perangkat Android fisik (dengan *USB Debugging* aktif) atau jalankan **Android Virtual Device (AVD)**.
   * Pastikan konfigurasi *Run* mengarah pada modul **`app`**.
   * Klik tombol **Run** (▶️) atau tekan `Shift + F10`.

4. **Menjalankan via Command Line (CLI)**  
   ```bash
   # Build APK Debug
   ./gradlew assembleDebug

   # Install APK ke perangkat/emulator
   ./gradlew installDebug
   ```
