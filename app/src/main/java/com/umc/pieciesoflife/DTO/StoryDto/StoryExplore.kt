package com.umc.pieciesoflife.DTO.StoryDto

data class StoryExplore(
    val dataList: List<StoryExploreData>,
    val messsage: String,
    val status: Int,
    val success: Boolean
)