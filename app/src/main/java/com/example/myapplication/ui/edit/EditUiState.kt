package com.example.myapplication.ui.edit

data class EditUiState(
    val name: String = "",
    val location: String = "",
    val description: String = ""
) {
    val isFormValid: Boolean
        get() = name.isNotBlank() &&
                location.isNotBlank() &&
                description.isNotBlank()
}