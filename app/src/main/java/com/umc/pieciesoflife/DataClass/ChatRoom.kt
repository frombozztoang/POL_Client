package com.umc.pieciesoflife.DataClass

data class ChatRoom(
    val users: Map<String, Boolean>? = HashMap(),
    var messages : Map<String, Message>? = HashMap(),
) : java.io.Serializable{
}