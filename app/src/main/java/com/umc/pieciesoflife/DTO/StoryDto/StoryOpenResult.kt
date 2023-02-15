package com.umc.pieciesoflife.DTO.StoryDto

data class StoryOpenResult(
    val `data`: StoryOpenResultData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)