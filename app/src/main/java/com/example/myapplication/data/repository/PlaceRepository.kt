package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.PlaceDao
import com.example.myapplication.data.local.entity.PlaceEntity
import com.example.myapplication.data.local.source.PlaceData.initialPlaces
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val dao: PlaceDao) {
    val places: Flow<List<PlaceEntity>> = dao.observeAll()

    suspend fun add(item: PlaceEntity) = dao.insert(item)

    suspend fun remove(item: PlaceEntity) = dao.delete(item)

    suspend fun update(item: PlaceEntity) = dao.update(item)

    suspend fun seedIfEmpty() {
        if (dao.count() == 0) {
            dao.insertAll(initialPlaces)
        }
    }
}
