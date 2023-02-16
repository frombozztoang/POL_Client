package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilterDataTagStory(
    val backgroundColor: String,
    val date: String,
    val description: String,
    val id: Int,
    val nickname: String,
    val profileImgUrl: String,
    var storyTag: String,
    val title: String,
    val userId: Int
)