package com.umc.pieciesoflife.DTO.MyPageDto

data class MyPage(
    val `data`: MyPageData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)