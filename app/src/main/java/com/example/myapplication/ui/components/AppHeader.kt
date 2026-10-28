package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppHeader() {
    Row(modifier = Modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondary)
        .padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text("Eorzea Places", color = MaterialTheme.colorScheme.onSecondary, style = MaterialTheme.typography.titleLarge)
    }
}