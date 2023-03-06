package com.umc.pieciesoflife.DTO.MyPageDto

import com.umc.pieciesoflife.DTO.ChatDTO.Chat

data class MyPageData(
    val chat: List<Chat>,
    val story: List<MyPageStory>
)