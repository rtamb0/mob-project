
package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.repository.PlaceRepository

class PlaceApplication : Application() {
    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val placeRepository by lazy {
        PlaceRepository(database.placeDao())
    }
}
