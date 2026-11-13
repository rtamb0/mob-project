package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.PlaceDao
import com.example.myapplication.data.local.entity.PlaceEntity
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val dao: PlaceDao) {
    val places: Flow<List<PlaceEntity>> = dao.observeAll()

    suspend fun add(name: String, location: String, imageId: Int, description: String) {
        dao.insert(PlaceEntity(name = name, location = location, imageId = imageId, description = description))
    }

    suspend fun remove(item: PlaceEntity) = dao.delete(item)

    suspend fun update(item: PlaceEntity) = dao.update(item)
}
