package com.example.myapplication.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.PlaceApplication
import com.example.myapplication.ui.home.HomeScreen
import com.example.myapplication.ui.home.Home
import com.example.myapplication.ui.detail.DetailScreen
import com.example.myapplication.ui.detail.Detail
import com.example.myapplication.ui.edit.Edit
import com.example.myapplication.ui.edit.EditRoute
import com.example.myapplication.ui.viewmodel.PlaceViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val application = LocalContext.current.applicationContext as PlaceApplication

    val placeViewModel = viewModel {
        PlaceViewModel(application.placeRepository)
    }

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> { HomeScreen(
            placeViewModel.places,
            onOpenDetail = { id ->
                navController.navigate(Detail(id = id))
            }
        ) }
        composable<Detail> {  entry ->
            val route = entry.toRoute<Detail>()
            val places by placeViewModel.places.collectAsStateWithLifecycle()
            val place = places.find { it.id == route.id }

            if (place != null) {
                DetailScreen(place, { navController.popBackStack() }, { navController.navigate(Edit(id = place.id)) })
            } else {
                Text("Item not found")
            }
        }
        composable<Edit> {  entry ->
            val route = entry.toRoute<Edit>()
            val places by placeViewModel.places.collectAsStateWithLifecycle()
            val place = places.find { it.id == route.id }

            if (place != null) {
                EditRoute(
                    place = place,
                    onBack = {
                        navController.popBackStack()
                    },
                    onSave = { updatedPlace ->
                        if (updatedPlace == null) return@EditRoute
                        placeViewModel.updatePlace(updatedPlace)
                    }
                )
            } else {
                Text("Item not found")
            }
        }
    }
}