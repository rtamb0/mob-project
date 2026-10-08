# Aplikasi Daftar Buku

## 1. Tujuan

Aplikasi ini dibuat untuk menampilkan dan mengelola daftar buku sederhana menggunakan Kotlin dan Jetpack Compose. Pengguna dapat mengurutkan buku berdasarkan nama secara alfabetis, memfilter buku berdasarkan nama tertentu, menghitung total halaman seluruh buku, dan mengembalikan daftar buku ke kondisi awal.

## 2. Model Data

Model data menggunakan `data class Book` dengan atribut berikut:

* `name: String` — nama buku.
* `genre: String` — genre buku.
* `author: String` — nama penulis buku.
* `pageCount: Int?` — jumlah halaman buku, dengan nilai awal `0` dan dapat bernilai `null`.

Data buku disimpan dalam `bookList`, yaitu sebuah daftar berisi objek `Book` yang digunakan sebagai sumber data utama aplikasi.

## 3. Logika Program

Aplikasi menggunakan beberapa fungsi untuk mengolah data buku:

* **`filterBookByName()`** — menyaring daftar buku berdasarkan nama yang sesuai dengan input menggunakan fungsi `filter()`.
* **`sortBookAlphabetically()`** — mengurutkan buku berdasarkan nama secara alfabetis menggunakan `sortedBy()` dan `lowercase()`.
* **`sumAllPageCounts()`** — menjumlahkan jumlah halaman seluruh buku menggunakan `fold()`. Nilai `null` dianggap sebagai `0`.

Tombol **Sort A–Z** mengurutkan daftar buku, **Show only Kizumonogatari** menampilkan buku berjudul *Kizumonogatari*, **Total page counts** menghitung total halaman berdasarkan daftar buku awal, dan **Reset** mengembalikan daftar yang ditampilkan ke `bookList`.
