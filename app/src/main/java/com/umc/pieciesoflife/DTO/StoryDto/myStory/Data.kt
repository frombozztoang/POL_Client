package com.umc.pieciesoflife.DTO.StoryDto.myStory

data class Data(
    val stories: List<Story>,
    val userPieceCnt: Int,
    val userStoryCnt: Int
)