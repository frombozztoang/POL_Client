package com.umc.pieciesoflife.DTO.ChatDTO

data class Chat(
    val `data`: ChatData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)