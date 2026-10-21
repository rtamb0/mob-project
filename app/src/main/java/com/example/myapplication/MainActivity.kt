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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = { AppHeader() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                innerPadding
                            )
                    ) {
                        AppBody()
                    }
                }
            }
        }
    }
}

@Parcelize
data class Book (
    val title: String,
    val author: String,
    val genre: String,
    val read: Boolean
) : Parcelable

@Composable
fun AppHeader() {
    Row(modifier = Modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondary)
        .padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text("Reading Tracker", color = MaterialTheme.colorScheme.onSecondary, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun AddButton() {
    var showDialog by remember { mutableStateOf(false) }

    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var genre by rememberSaveable { mutableStateOf("") }
    var isRead by rememberSaveable { mutableStateOf(false) }

    val isValid = title.isNotBlank() && author.isNotBlank() && genre.isNotBlank()

    Button(onClick = { showDialog = true }) {
        Text("Add")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add New Book") },
            text = { AddBookForm(
                title = title,
                onTitleChange = { title = it },
                author = author,
                onAuthorChange = { author = it },
                genre = genre,
                onGenreChange = { genre = it },
                isRead = isRead,
                onIsReadChange = { isRead = it }
            ) },
            confirmButton = {
                Button(
                    enabled = isValid,
                    onClick = {
                        title = ""; author = ""; genre = ""; isRead = false
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun BookCard(book: Book, onReadChange: (Boolean) -> Unit) {
    Card {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(book.title, style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
                Checkbox(book.read, onReadChange,  modifier = Modifier.scale(0.8f) )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(book.author, style = MaterialTheme.typography.bodySmall)
                Text(book.genre, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun BookGrid(books: List<Book>, onReadChange: (Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
        items(books.size) { index ->
            BookCard(book = books[index], onReadChange = { onReadChange(index) })
        }
    })
}

@Composable
fun AppBody() {
    val bookList = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { mutableStateListOf(*it.toTypedArray()) }
        )
    ) {
        mutableStateListOf(
            Book("Kizumonogatari", "Nisio Isin", "Supernatural", true),
            Book("And Then There Were None", "Agatha Christie", "Mystery", false),
            Book("Lord of the Rings", "J.R.R. Tolkien", "Fantasy", true),
            Book("The Alchemist", "Paulo Coelho", "Adventure", false),
        )
    }

    val onReadChange = { index: Int ->
        bookList[index] = bookList[index].copy(read = !bookList[index].read)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AddButton()
        BookGrid(bookList, onReadChange)
    }
}

@Composable
fun AddBookForm(
    title: String,
    onTitleChange: (String) -> Unit,
    author: String,
    onAuthorChange: (String) -> Unit,
    genre: String,
    onGenreChange: (String) -> Unit,
    isRead: Boolean,
    onIsReadChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Book Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = onAuthorChange,
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = genre,
            onValueChange = onGenreChange,
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Is read?")
            Checkbox(isRead, onIsReadChange)
        }
    }
}