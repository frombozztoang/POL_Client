package com.umc.pieciesoflife.DTO.ChatDTO

data class ChatData(
    val chats: List<ChatMessage>,
    val story: ChatStory
)