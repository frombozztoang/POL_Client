package com.umc.pieciesoflife.DTO.StoryDto

data class StoryLike(
    val `data`: StoryLikeData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)