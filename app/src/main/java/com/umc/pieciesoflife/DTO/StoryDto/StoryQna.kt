package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.SerializedName

data class StoryQna(
    @SerializedName("tagId") val tagId: Int,
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String
)