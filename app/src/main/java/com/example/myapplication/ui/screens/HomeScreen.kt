package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Book
import com.example.myapplication.ui.components.AddButton
import com.example.myapplication.ui.components.BookGrid
import kotlinx.serialization.Serializable

@Serializable
object Home
@Composable
fun HomeScreen() {
    val bookList = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { mutableStateListOf(*it.toTypedArray()) }
        )
    ) {
        mutableStateListOf(
            Book(1,"Kizumonogatari", "Nisio Isin", "Supernatural", true),
            Book(2, "And Then There Were None", "Agatha Christie", "Mystery", false),
            Book(3, "Lord of the Rings", "J.R.R. Tolkien", "Fantasy", true),
            Book(4, "The Alchemist", "Paulo Coelho", "Adventure", false),
        )
    }

    val sortedBooks by remember {
        derivedStateOf {
            bookList.sortedBy { it.read }
        }
    }

    val lastId by remember {
        derivedStateOf {
            bookList.lastOrNull()?.id ?: 0
        }
    }

    val onReadChange = { id: Int ->
        val index = bookList.indexOfFirst { it.id == id }

        if (index != -1) {
            val book = bookList[index]
            bookList[index] = book.copy(read = !book.read)
        }
    }

    val onSubmit = { book: Book -> bookList.add(book) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AddButton(onSubmit, lastId)
        BookGrid(sortedBooks, onReadChange)
    }
}
