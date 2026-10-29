package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place (
    val id: Int,
    var name: String,
    var location: String,
    val imageId: Int,
    var description: String
) : Parcelable