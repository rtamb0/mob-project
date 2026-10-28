package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.Home
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    // 1. Create the controller that manages the backstack
    val navController = rememberNavController()

    // 2. Set up the NavHost container and define the NavGraph inside it
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> { HomeScreen() }
    }
}