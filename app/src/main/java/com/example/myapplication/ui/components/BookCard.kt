package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Book

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
