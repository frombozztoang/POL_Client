package com.umc.pieciesoflife.DTO.StoryDto

data class StoryDetailStory(
    val color: String,
    val date: String,
    val description: String,
    val id: Int,
    val likeCnt: Int,
    val liked: Boolean,
    val open: Boolean,
    val main: Boolean,
    val nickname: String,
    val profileImgUrl: String,
    val title: String,
    val writerId: Int,
    val myId: Int
)