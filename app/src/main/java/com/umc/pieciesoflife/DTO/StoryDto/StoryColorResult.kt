package com.umc.pieciesoflife.DTO.StoryDto

data class StoryColorResult(
    val `data`: StoryColorResultData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)