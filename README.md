# Eorzea Places Application

Aplikasi Android berbasis **Jetpack Compose**, **Navigation Compose (Type-Safe Navigation)**, **Architecture Components (ViewModel & StateFlow)**, dan **Material 3** untuk menampilkan dan mengelola katalog tempat wisata/lokasi di dunia Eorzea (*Final Fantasy XIV*).

Aplikasi ini menerapkan arsitektur Android modern (**UI Layer + ViewModel + StateFlow**) dengan prinsip *Unidirectional Data Flow* (UDF), pemisahan antara *Stateful Route* dan *Stateless Screen*, serta pengelolaan state yang aman terhadap perubahan konfigurasi (*configuration changes*).

---

## 1. Tentang Aplikasi

**Eorzea Places** adalah aplikasi seluler yang dirancang untuk menampilkan berbagai lokasi dan tempat legendaris di Eorzea. Aplikasi ini memanfaatkan komponen UI modern dari **Jetpack Compose** serta navigasi *type-safe* menggunakan `androidx.navigation`.

### Fitur Utama
* 🗺️ **Katalog Tempat (Grid View)**: Menampilkan lokasi-lokasi dalam tata letak kisi 2 kolom (`LazyVerticalGrid`) di `HomeScreen`.
* 🔍 **Detail Tempat (Detail View)**: Menampilkan informasi lengkap lokasi (Gambar, Nama, Lokasi, Deskripsi) pada `DetailScreen`.
* ✏️ **Pengeditan Tempat (Edit View)**: Pengguna dapat memperbarui Nama, Lokasi, dan Deskripsi tempat secara interaktif melalui `EditScreen` dengan validasi form secara *real-time*.
* 🧭 **Type-Safe Navigation**: Menggunakan `@Serializable` objek/class rute (`Home`, `Detail(id: Int)`, `Edit(id: Int)`) untuk navigasi antar layar yang aman tipe data.
* ⚡ **ViewModel & StateFlow Architecture**: Menggunakan `EditViewModel` dan `EditUiState` dengan enkapsulasi `MutableStateFlow` / `StateFlow` serta konsumsi state yang *lifecycle-aware* (`collectAsStateWithLifecycle()`).
* 🛡️ **Form Validation & Non-Happy Path Handling**: Memvalidasi input form secara otomatis (`isFormValid`), sehingga tombol *Save* hanya aktif jika semua field terisi dan input valid.
* 💾 **State Retention & Rotation Consistency**: State form bertahan saat terjadi rotasi layar (Portrait ↔ Landscape) karena dikelola oleh `ViewModel`, sementara daftar tempat dipertahankan dengan `rememberSaveable` (`listSaver`).

---

## 2. Arsitektur & Struktur Kode

Struktur kode proyek terorganisasi ke dalam beberapa package utama di [app/src/main/java/com/example/myapplication](app/src/main/java/com/example/myapplication):

```
com.example.myapplication/
├── model/
│   └── Place.kt              # Data class representasi tempat (Parcelable)
├── navigation/
│   └── AppNavigation.kt       # Setup NavHost, Type-Safe Routes, & state retention list
└── ui/
    ├── App.kt                # Container utama aplikasi (AppHeader + AppNavigation)
    ├── components/
    │   ├── AppHeader.kt      # Header judul aplikasi
    │   └── PlaceCard.kt      # Card item tempat di grid
    ├── home/
    │   └── HomeScreen.kt     # Layar Katalog Utama (Grid View)
    ├── detail/
    │   └── DetailScreen.kt   # Layar Detail Tempat
    └── edit/                 # UI Layer + ViewModel + StateFlow Architecture
        ├── EditRoute.kt      # Stateful Route: Hubungan ViewModel, State, & Callbacks
        ├── EditScreen.kt     # Stateless Screen: UI Murni (Menerima UiState & Callbacks)
        ├── EditUiState.kt    # Immutable State: State form & logika validasi (isFormValid)
        └── EditViewModel.kt  # Screen-Level State Holder: StateFlow & event handler
```

### Data Model
* **`Place`** ([Place.kt](app/src/main/java/com/example/myapplication/model/Place.kt))  
  `Parcelable` data class yang merepresentasikan data tempat: `id`, `name`, `location`, `imageId`, dan `description`.

### Navigasi
* **`AppNavigation()`** ([AppNavigation.kt](app/src/main/java/com/example/myapplication/navigation/AppNavigation.kt))  
  Mengelola *state* daftar tempat (`placeList`), `NavHost`, dan rute navigasi *type-safe* (`Home`, `Detail(id: Int)`, `Edit(id: Int)`).

### Pola Arsitektur UI Layer & ViewModel

Pola UI Layer pada fitur edit mengikuti rekomendasi arsitektur Android modern:

1. **`EditUiState`** ([EditUiState.kt](app/src/main/java/com/example/myapplication/ui/edit/EditUiState.kt))  
   `data class` *immutable* yang menyimpan nilai bidang form (`name`, `location`, `description`) serta properti terhitung `isFormValid` untuk memastikan seluruh field tidak kosong.

2. **`EditViewModel`** ([EditViewModel.kt](app/src/main/java/com/example/myapplication/ui/edit/EditViewModel.kt))  
   *Screen-level state holder* yang mengenkapsulasi state dalam `private val _uiState = MutableStateFlow(...)` dan mengeksposnya secara *read-only* melalui `val uiState = _uiState.asStateFlow()`. ViewModel memproses event pengguna (`onNameChange`, `onLocationChange`, `onDescriptionChange`, `onSave`) dan tidak menyimpan referensi `NavController`.

3. **`EditRoute`** ([EditRoute.kt](app/src/main/java/com/example/myapplication/ui/edit/EditRoute.kt))  
   *Stateful Composable* yang berfungsi sebagai perantara antara ViewModel dan UI. Menginisialisasi `EditViewModel`, mengonsumsi `uiState` menggunakan `collectAsStateWithLifecycle()`, serta meneruskan state dan callback event ke `EditScreen`.

4. **`EditScreen`** ([EditScreen.kt](app/src/main/java/com/example/myapplication/ui/edit/EditScreen.kt))  
   *Stateless Composable* murni yang hanya menerima `EditUiState` dan fungsi *callback* event. Bebas dari logika navigasi maupun pembuatan state, sehingga mudah di-preview dan diuji.

### Layar & Komponen UI Lainnya
* **`AppHeader()`** ([AppHeader.kt](app/src/main/java/com/example/myapplication/ui/components/AppHeader.kt)): Header aplikasi di bagian atas yang menampilkan judul *"Eorzea Places"*.
* **`HomeScreen(...)`** ([HomeScreen.kt](app/src/main/java/com/example/myapplication/ui/home/HomeScreen.kt)): Layar utama yang menampilkan daftar tempat dalam kisi 2 kolom (`LazyVerticalGrid`).
* **`PlaceCard(...)`** ([PlaceCard.kt](app/src/main/java/com/example/myapplication/ui/components/PlaceCard.kt)): Komponen kartu interaktif yang menampilkan gambar, nama, dan lokasi tempat.
* **`DetailScreen(...)`** ([DetailScreen.kt](app/src/main/java/com/example/myapplication/ui/detail/DetailScreen.kt)): Layar detail tempat beserta tombol *Back* dan tombol *Edit*.

---

## 3. Cara Menjalankan

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
