package com.umc.pieciesoflife.DTO.MyPageDto

data class Chat(
    val content: String,
    val date: String,
    val id: Int,
    val nickname: String,
    val profileImgUrl: String
)