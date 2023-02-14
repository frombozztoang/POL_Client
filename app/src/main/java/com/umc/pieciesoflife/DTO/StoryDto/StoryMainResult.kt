package com.umc.pieciesoflife.DTO.StoryDto

data class StoryMainResult(
    val `data`: StoryMainResultData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)