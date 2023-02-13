package com.umc.pieciesoflife.DTO.StoryDto

data class StoryPostResult(
    val `data`: StoryPostResultData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)