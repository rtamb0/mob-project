package com.example.myapplication.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.local.entity.PlaceEntity

@Composable
fun EditRoute(place: PlaceEntity, onBack: () -> Unit, onSave: (PlaceEntity?) -> Unit) {
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
        onSave = {
            viewModel.onSave().let(onSave)
        }
    )
}