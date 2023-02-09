package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("questionTemplate")
    @Expose
    val questionTemplate: String
)


