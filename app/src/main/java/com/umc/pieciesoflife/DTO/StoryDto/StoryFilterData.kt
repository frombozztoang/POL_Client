package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilterData(
    var storyTags: List<StoryFilterDataTag>,
    val userPieceCnt: Int,
    val userStoryCnt: Int
)