package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place (
    val id: Int,
    val name: String,
    val location: String,
    val imageId: Int,
    val description: String
) : Parcelable