package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryDetailExploreQuestion(
    @SerializedName("answer") @Expose val answer: String,
    @SerializedName("question") @Expose val question: String,
    @SerializedName("tagCategoryId") @Expose val tagCategoryId: Int
)