package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val read: Boolean
) : Parcelable