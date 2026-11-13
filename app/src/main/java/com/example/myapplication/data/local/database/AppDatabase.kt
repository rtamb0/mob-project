
package com.example.myapplication.data.local.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.myapplication.data.local.dao.PlaceDao
import com.example.myapplication.data.local.entity.PlaceEntity

@Database(
    entities = [PlaceEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "place_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
