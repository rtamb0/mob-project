package com.example.myapplication.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Place

@Composable
fun EditRoute(place: Place, onBack: () -> Unit) {
    val viewModel = viewModel {
        EditViewModel(place)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EditScreen(
        place = place,
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onLocationChange = viewModel::onLocationChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onBack = onBack,
        onSave = viewModel::onSave
    )
}