package com.umc.pieciesoflife.DTO.ChatDTO

import com.google.gson.annotations.SerializedName

data class ChatContent(
    @SerializedName("date") val date : String,
    @SerializedName("RoomId") val roomId : String,
    @SerializedName("myId") val myId : Int,
    @SerializedName("writerId") val writerId : Int
)
