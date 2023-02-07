package com.umc.pieciesoflife.DataClass

data class ChatMessage(
    var userId: String,
    var date: String,
    var content: String
) : java.io.Serializable{

}

