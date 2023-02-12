package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilter(
    val dataList: List<StoryFilterData>,
    val messsage: String,
    val status: Int,
    val success: Boolean
)