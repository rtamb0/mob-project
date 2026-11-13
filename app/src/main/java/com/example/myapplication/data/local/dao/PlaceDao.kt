package com.example.myapplication.data.local.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.myapplication.data.local.entity.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place ORDER BY name")
    fun observeAll(): Flow<List<PlaceEntity>>

    @Insert
    suspend fun insert(item: PlaceEntity)

    @Update
    suspend fun update(item: PlaceEntity)

    @Delete
    suspend fun delete(item: PlaceEntity)
}
