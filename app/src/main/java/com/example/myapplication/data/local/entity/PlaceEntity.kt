package com.example.myapplication.data.local.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var location: String,
    val imageId: Int,
    var description: String
)
