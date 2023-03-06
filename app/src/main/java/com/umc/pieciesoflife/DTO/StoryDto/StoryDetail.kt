package com.umc.pieciesoflife.DTO.StoryDto

data class StoryDetail(
    val `data`: StoryDetailData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)