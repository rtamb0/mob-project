package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.entity.PlaceEntity
import com.example.myapplication.data.local.source.PlaceData.initialPlaces
import com.example.myapplication.data.repository.PlaceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val repository: PlaceRepository
) : ViewModel() {

    val places = repository.places
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedIfEmpty()
        }
    }

    fun addPlace(place: PlaceEntity) {
        viewModelScope.launch {
            repository.add(place)
        }
    }

    fun removePlace(place: PlaceEntity) {
        viewModelScope.launch {
            repository.remove(place)
        }
    }

    fun updatePlace(place: PlaceEntity) {
        viewModelScope.launch {
            repository.update(place)
        }
    }
}