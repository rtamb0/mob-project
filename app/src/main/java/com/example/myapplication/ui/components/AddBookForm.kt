//package com.example.myapplication.ui.components
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun AddBookForm(
//    title: String,
//    onTitleChange: (String) -> Unit,
//    author: String,
//    onAuthorChange: (String) -> Unit,
//    genre: String,
//    onGenreChange: (String) -> Unit,
//    isRead: Boolean,
//    onIsReadChange: (Boolean) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        OutlinedTextField(
//            value = title,
//            onValueChange = onTitleChange,
//            label = { Text("Book Title") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = author,
//            onValueChange = onAuthorChange,
//            label = { Text("Author") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        OutlinedTextField(
//            value = genre,
//            onValueChange = onGenreChange,
//            label = { Text("Genre") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text("Is read?")
//            Checkbox(isRead, onIsReadChange)
//        }
//    }
//}