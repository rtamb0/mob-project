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

    @Query("SELECT COUNT(*) FROM place")
    suspend fun count(): Int

    @Insert
    suspend fun insert(item: PlaceEntity)

    @Insert
    suspend fun insertAll(items: List<PlaceEntity>)

    @Update
    suspend fun update(item: PlaceEntity)

    @Delete
    suspend fun delete(item: PlaceEntity)
}
