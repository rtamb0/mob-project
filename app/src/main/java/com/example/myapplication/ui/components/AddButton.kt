package com.example.myapplication.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.myapplication.model.Book

@Composable
fun AddButton(onSubmit: (Book) -> Boolean, lastId: Int) {
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
                        onSubmit(Book(lastId + 1, title, author, genre, isRead))
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