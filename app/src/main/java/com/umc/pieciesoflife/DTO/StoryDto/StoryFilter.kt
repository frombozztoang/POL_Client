package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilter(
    val `data`: Data,
    val messsage: String,
    val status: Int,
    val success: Boolean
)