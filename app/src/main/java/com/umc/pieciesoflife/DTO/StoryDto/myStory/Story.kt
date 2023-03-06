package com.umc.pieciesoflife.DTO.StoryDto.myStory

data class Story(
    val backgroundColor: String,
    val date: String,
    val description: String,
    val id: Int,
    val nickname: String,
    val profileImgUrl: String,
    val title: String,
    val userId: Int
)