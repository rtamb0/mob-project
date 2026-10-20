package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(topBar = { AppHeader() }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppBody()
                    }
                }
            }
        }
    }
}

class Book {
    val title: String
    val author: String
    val genre: String
    val read: Boolean

    constructor(title: String, author: String, genre: String, read: Boolean) {
        this.title = title
        this.author = author
        this.genre = genre
        this.read = read
    }
}

@Composable
fun AppHeader() {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(16.dp),) {
        Text("Reading Tracker")
    }
}

@Composable
fun AddButton() {
    Button(onClick = { /* aksi tambah */ }) {
        Text("Tambah")
    }
}

@Composable
fun BookCard(book: Book, onReadChange: (Boolean) -> Unit) {
    Card {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(book.title, style = MaterialTheme.typography.headlineSmall)
                Checkbox(book.read, onReadChange)
            }
            Column {
                Text(book.author)
                Text(book.genre)
            }
        }
    }
}

//@Composable
//fun BookRow(books: List<Book>) {
//    FlowRow(modifier = Modifier.fillMaxWidth()) {
//        books
//    }
//})

@Composable
fun AppBody() {
    Column(modifier = Modifier.fillMaxSize()) {
        AddButton()
        BookCard(book = Book("Title", "Author", "Genre", true), onReadChange = { /* aksi baca */ })
    }
}

@Composable
fun NotificationSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Aktifkan notifikasi")
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun InteractiveProfileScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var notificationEnabled by rememberSaveable { mutableStateOf(false) }

    val isValid = name.length >= 3 && email.contains("@")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        NotificationSwitch(
            checked = notificationEnabled,
            onCheckedChange = { notificationEnabled = it }
        )

        Button(
            enabled = isValid,
            onClick = { /* aksi simpan */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}