package com.umc.pieciesoflife.DTO.ChatDTO

data class ChatStory(
    val color: String,
    val date: String,
    val description: String,
    val id: Int,
    val likeCnt: Int,
    val liked: Boolean,
    val nickname: String,
    val profileImgUrl: String,
    val title: String
)