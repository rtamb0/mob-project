package com.example.myapplication.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.myapplication.data.local.dao.PlaceDao
import com.example.myapplication.data.local.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
