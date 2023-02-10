package com.umc.pieciesoflife.DTO.QuestionDto

import com.google.gson.annotations.SerializedName

data class QuestionData(
    @SerializedName("questionTemplate") val questionTemplate: String
)