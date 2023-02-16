package com.umc.pieciesoflife.DTO.StoryDto

data class Data(
    val storyTags: List<StoryTagX>,
    val userPieceCnt: Int,
    val userStoryCnt: Int
)