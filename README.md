# Eorzea Places Application

Aplikasi Android berbasis **Jetpack Compose**, **Navigation Compose (Type-Safe Navigation)**, dan **Material 3** untuk menampilkan dan mengelola katalog tempat wisata/lokasi di dunia Eorzea.

---

## 1. Tentang Aplikasi

**Eorzea Places** adalah aplikasi seluler yang dirancang untuk menampilkan berbagai lokasi dan tempat legendaris di Eorzea (*Final Fantasy XIV*). Aplikasi ini memanfaatkan komponen UI modern dari **Jetpack Compose** serta navigasi *type-safe* menggunakan `androidx.navigation`.

### Fitur Utama
* 🗺️ **Katalog Tempat (Grid View)**: Menampilkan lokasi-lokasi dalam tata letak kisi 2 kolom (`LazyVerticalGrid`) di `HomeScreen`.
* 🔍 **Detail Tempat (Detail View)**: Menampilkan informasi lengkap lokasi (Gambar, Nama, Lokasi, Deskripsi) pada `DetailScreen`.
* ✏️ **Pengeditan Tempat (Edit View)**: Pengguna dapat memperbarui Nama, Lokasi, dan Deskripsi tempat secara langsung melalui `EditScreen`.
* 🧭 **Type-Safe Navigation**: Menggunakan `@Serializable` objek/class rute (`Home`, `Detail(id: Int)`, `Edit(id: Int)`) untuk navigasi antar layar yang aman tipe data.
* 💾 **State Retention**: Menggunakan `rememberSaveable` dengan `listSaver` di `AppNavigation` sehingga perubahan data tempat tetap tersimpan saat terjadi rotasi layar atau pemulihan *activity*.

---

## 2. Struktur Komponen & Kode Utama

Struktur kode proyek terorganisasi ke dalam beberapa package utama di [app/src/main/java/com/example/myapplication](app/src/main/java/com/example/myapplication):

### Data Model
* **`Place`** ([Place.kt](app/src/main/java/com/example/myapplication/model/Place.kt))  
  `Parcelable` data class yang merepresentasikan data tempat:
  * `id`: ID unik tempat (`Int`).
  * `name`: Nama tempat (`String`).
  * `location`: Wilayah / lokasi (`String`).
  * `imageId`: Resource ID gambar (`Int`).
  * `description`: Deskripsi lengkap tempat (`String`).

### Navigasi
* **`AppNavigation()`** ([AppNavigation.kt](app/src/main/java/com/example/myapplication/navigation/AppNavigation.kt))  
  Mengelola *state* daftar tempat (`placeList`), `NavHost`, dan rute navigasi:
  * `Home`: Rute ke halaman utama (`HomeScreen`).
  * `Detail(id: Int)`: Rute ke halaman detail (`DetailScreen`).
  * `Edit(id: Int)`: Rute ke halaman edit (`EditScreen`).

### Layar & Komponen UI
* **`AppHeader()`** ([AppHeader.kt](app/src/main/java/com/example/myapplication/ui/components/AppHeader.kt))  
  Header aplikasi di bagian atas yang menampilkan judul *"Eorzea Places"*.
* **`HomeScreen(...)`** ([HomeScreen.kt](app/src/main/java/com/example/myapplication/ui/screens/HomeScreen.kt))  
  Layar utama yang menampilkan daftar tempat dalam bentuk kisi 2 kolom (`LazyVerticalGrid`).
* **`PlaceCard(...)`** ([PlaceCard.kt](app/src/main/java/com/example/myapplication/ui/components/PlaceCard.kt))  
  Komponen kartu interaktif yang menampilkan gambar, nama, dan lokasi tempat.
* **`DetailScreen(...)`** ([DetailScreen.kt](app/src/main/java/com/example/myapplication/ui/screens/DetailScreen.kt))  
  Layar yang menampilkan detail lengkap tempat beserta tombol *Back* dan tombol *Edit*.
* **`EditScreen(...)`** ([EditScreen.kt](app/src/main/java/com/example/myapplication/ui/screens/EditScreen.kt))  
  Layar formulir untuk mengubah data tempat dengan validasi input pada tombol *Save*.

---

## 3. Cara Menjalankan

### Prasyarat
* **Android Studio**: Android Studio Ladybug atau versi yang lebih baru.
* **JDK**: Java Development Kit (JDK) 11 atau lebih baru.
* **Min SDK**: Level 24 (Android 7.0 Nougat) atau lebih baru.
* **Target / Compile SDK**: Level 37 / Android 15.

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
