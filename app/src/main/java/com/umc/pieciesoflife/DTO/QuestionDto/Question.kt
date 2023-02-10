package com.umc.pieciesoflife.DTO.QuestionDto

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("data") val `data`: QuestionData,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean
)