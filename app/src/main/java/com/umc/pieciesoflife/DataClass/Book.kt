package com.umc.pieciesoflife.DataClass

import android.database.Observable
import androidx.databinding.ObservableField

data class Book(
    val title : String,
    val date : String,
    val profileImg : Int,
    val content : String,
    val postTitle : String,
    val postImg : Int,
    val userName : String
)
