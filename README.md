# Aplikasi Komunitas Gaming IF UAI

Aplikasi Android berbasis **Jetpack Compose** yang menampilkan halaman utama dan informasi mengenai **Komunitas Gaming IF UAI**.

---

## 1. Tujuan Aplikasi

Aplikasi ini dibuat sebagai media informasi untuk **Komunitas Gaming IF UAI** (Informatika Universitas Al Azhar Indonesia). Tujuan utama aplikasi meliputi:
* **Memperkenalkan Komunitas**: Menjelaskan profil dan latar belakang komunitas gaming bagi mahasiswa Informatika UAI.
* **Informasi Aktivitas**: Menampilkan daftar kegiatan rutin komunitas, seperti main *co-op* bareng, 1v1 *fighting game*, *sharing session*, hingga kumpul di *event game*.
* **Pendaftaran Anggota**: Menyediakan tombol ajakan bertindak (*call-to-action*) bagi mahasiswa yang tertarik untuk mendaftarkan diri.

---

## 2. Cara Menjalankan

### Prasyarat
* **Android Studio**: Versi terbaru (direkomendasikan Android Studio Ladybug atau yang lebih baru).
* **Java Development Kit (JDK)**: JDK 11 atau lebih baru.
* **Perangkat / Emulator**: Perangkat Android fisik atau Emulator dengan Minimum SDK Level 24 (Android 7.0 Nougat).

### Langkah-Langkah

1. **Buka Project**  
   Buka aplikasi Android Studio, pilih **Open**, lalu pilih folder direktori project ini (`mob-project`).

2. **Sinkronisasi Gradle**  
   Tunggu hingga proses *Gradle Sync* selesai. Jika tidak berjalan otomatis, klik **File > Sync Project with Gradle Files**.

3. **Jalankan Aplikasi**  
   * Hubungkan perangkat Android fisik via USB (*USB Debugging* aktif) atau jalankan **Android Virtual Device (AVD) / Emulator**.
   * Pastikan konfigurasi *Run* memilih modul **`app`**.
   * Klik tombol **Run** (ikon ▶️) atau tekan tombol keyboard `Shift + F10`.

4. **Menjalankan via Terminal / CLI (Opsional)**  
   ```bash
   # Melakukan build APK Debug
   ./gradlew assembleDebug

   # Meng-install APK langsung ke perangkat/emulator
   ./gradlew installDebug
   ```

---

## 3. Struktur Composable Utama

Antarmuka pengguna (UI) dibangun menggunakan **Jetpack Compose** di [MainActivity.kt](app/src/main/java/com/example/myapplication/MainActivity.kt) dengan struktur komponen `@Composable` sebagai berikut:

* **`LayoutScreen()`**  
  * **Fungsi**: Kontainer utama layar layout.  
  * **Detail**: Menggunakan layout `Column` berskorl (`verticalScroll`) yang membungkus komponen `PageHeader`, `InfoItem` (About & Aktivitas), dan `ActionCard`. Juga menerapkan `navigationBarsPadding()` agar aman dari *insets* sistem bar.

* **`PageHeader()`**  
  * **Fungsi**: Komponen header visual di bagian atas layar.  
  * **Detail**: Menggunakan layout `Box` yang menumpuk gambar latar (`R.drawable.header_background`) berfitur efek blur (`Modifier.blur(8.dp)`), serta teks judul *"Komunitas Gaming IF UAI"* dan subjudul sambutan.

* **`InfoItem(label: String, value: String)`**  
  * **Fungsi**: Komponen kartu reusabel (`Card`) untuk menampilkan blok informasi.  
  * **Penggunaan**:  
    * **About**: Menjelaskan identitas dan keterbukaan komunitas untuk berbagai genre game.  
    * **Aktivitas**: Menampilkan poin-poin kegiatan rutin yang dilakukan anggota.

* **`ActionCard()`**  
  * **Fungsi**: Komponen kartu ajakan bertindak (*Call-to-Action*).  
  * **Detail**: Berisi teks ajakan pendaftaran serta komponen `Button` bertuliskan *"Daftar Sekarang!"*.
