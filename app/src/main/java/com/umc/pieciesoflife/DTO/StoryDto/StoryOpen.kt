package com.umc.pieciesoflife.DTO.StoryDto

data class StoryOpen(
    val `data`: StoryOpenData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)