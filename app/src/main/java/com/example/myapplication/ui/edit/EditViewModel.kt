package com.example.myapplication.ui.edit

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.local.entity.PlaceEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditViewModel(
    private val place: PlaceEntity
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditUiState(
        name = place.name,
        location = place.location,
        description = place.description
    ))

    val uiState = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun onLocationChange(value: String) {
        _uiState.update { it.copy(location = value) }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(description = value) }
    }

    fun onSave(): PlaceEntity? {
        if (!_uiState.value.isFormValid) return null
        place.name = _uiState.value.name
        place.location = _uiState.value.location
        place.description = _uiState.value.description

        return place
    }
}