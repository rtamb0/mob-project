# Reading Tracker Application

Aplikasi Android berbasis **Jetpack Compose** dan **Material 3** untuk mencatat dan mengelola daftar bacaan buku (**Reading Tracker**).

---

## 1. Tentang Aplikasi

**Reading Tracker** adalah aplikasi seluler sederhana yang dirancang untuk membantu pengguna melacak buku-buku yang sedang atau telah dibaca. Aplikasi ini memberikan antarmuka pengguna yang responsif, modern, dan bersih menggunakan **Jetpack Compose**.

### Fitur Utama
* 📚 **Daftar Buku (Grid View)**: Menampilkan koleksi buku dalam tata letak kisi 2 kolom (`LazyVerticalGrid`) yang rapi.
* ➕ **Tambah Buku Baru**: Modal dialog (`AlertDialog`) interaktif untuk menambahkan buku baru lengkap dengan Judul (*Title*), Penulis (*Author*), Genre (*Genre*), dan Status Baca.
* ✅ **Melacak Status Baca**: Pengguna dapat dengan mudah mengubah status baca (*Is Read*) melalui *Checkbox* pada setiap kartu buku.
* 🔄 **Pengurutan Otomatis**: Buku diurutkan secara dinamis berdasarkan status pembacaannya.
* 💾 **State Retention**: Menggunakan `rememberSaveable` dengan `listSaver` sehingga data daftar buku tetap tersimpan dan tidak hilang saat rotasi layar atau pemulihan *activity*.

---

## 2. Struktur Komponen & Kode Utama

Seluruh antarmuka pengguna dibangun secara deklaratif menggunakan Jetpack Compose di [MainActivity.kt](app/src/main/java/com/example/myapplication/MainActivity.kt).

### Data Model
* **`Book`**  
  `Parcelable` data class yang merepresentasikan data buku:
  * `id`: ID unik buku (`Int`).
  * `title`: Judul buku (`String`).
  * `author`: Penulis buku (`String`).
  * `genre`: Genre buku (`String`).
  * `read`: Status apakah buku sudah dibaca (`Boolean`).

### Komponen Composable
* **`AppHeader()`**  
  Header aplikasi di bagian atas layar yang menampilkan judul *"Reading Tracker"* dengan latar belakang tema sekunder.
* **`AppBody()`**  
  Kontainer utama layar yang mengelola *state* daftar buku (`bookList`), mengurutkan daftar buku, serta menyediakan *handler* untuk penambahan dan pembaruan status baca buku.
* **`BookGrid(books: List<Book>, onReadChange: (Int) -> Unit?)`**  
  Menampilkan daftar buku dalam bentuk kisi 2 kolom menggunakan `LazyVerticalGrid`.
* **`BookCard(book: Book, onReadChange: (Boolean) -> Unit)`**  
  Kartu (`Card`) individual yang menampilkan detail informasi buku (Judul, Penulis, Genre) serta *Checkbox* untuk mengubah status baca.
* **`AddButton(onSubmit: (Book) -> Boolean, lastId: Int)`**  
  Tombol untuk membuka dialog modal penambahan buku baru (`AddBookForm`). Memiliki validasi input sehingga tombol konfirmasi hanya aktif jika seluruh bidang teks diisi.
* **`AddBookForm(...)`**  
  Formulir dalam dialog modal yang berisi beberapa `OutlinedTextField` (Judul, Penulis, Genre) dan `Checkbox` untuk status baca.

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
