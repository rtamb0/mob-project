package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.screens.AppBody
import com.example.myapplication.ui.components.AppHeader

@Composable
fun App() {
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