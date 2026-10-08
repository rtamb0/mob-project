package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableIntStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current) + 24.dp,
                            top = innerPadding.calculateTopPadding() + 24.dp,
                            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current) + 24.dp,
                            bottom = innerPadding.calculateBottomPadding() + 24.dp
                        )) {
                        var shownList by remember { mutableStateOf(bookList) }
                        var totalPageCount by remember { mutableIntStateOf(0) }

                        Column(modifier = Modifier.fillMaxSize()) {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(onClick = { shownList = sortBookAlphabetically(shownList) }) {
                                    Text("Sort A–Z")
                                }
                                Button(onClick = { shownList = filterBookByName("Kizumonogatari", shownList) }) {
                                    Text("Show only Kizumonogatari")
                                }
                                Button(onClick = { totalPageCount = sumAllPageCounts(bookList) }) {
                                    Text("Total page counts")
                                }
                                Button(onClick = { shownList = bookList }) {
                                    Text("Reset")
                                }
                            }

                            Text("Total page counts: $totalPageCount")

                            Text("List of Books:")
                            for (book in shownList) {
                                Text("${book.name} — ${book.author} Pages ${book.pageCount}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Introduction(name: String, nim: String, modifier: Modifier = Modifier) {
    Text(
        text = "My name is $name - $nim",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

data class Book(val name: String, val genre: String, val author: String, val pageCount: Int? = 0)

val bookList = listOf<Book>(
    Book("Kizumonogatari", "Supernatural", "Nisio Ishin", 278),
    Book("Re Zero", "Psychological", "Tappei Nagasuki"),
    Book("Umineko no Naku Koro Ni", "Mystery", "Ryukishi07", 10000),
    Book("Clannad", "Drama", "Jun Maeda", 8000),
    Book("Mushoku Tensei", "Adventure", "Rifujin"),
    Book("Steins;Gate", "Sci-fi", "Nitroplus", 6000),
    Book("The Amazing Spider-Man Vol.1", "Action", "Stan Lee", 300),
    Book("And Then There Were None", "Mystery", "Agatha Christie", 400),
    )

fun filterBookByName (input: String, books: List<Book>) : List<Book> = books.filter({ book -> book.name == input })

fun sortBookAlphabetically (books: List<Book>) : List<Book> = books.sortedBy { it.name.lowercase() }

fun sumAllPageCounts (books: List<Book>): Int =
    books.fold(0) { acc, book -> acc + (book.pageCount ?: 0) }
