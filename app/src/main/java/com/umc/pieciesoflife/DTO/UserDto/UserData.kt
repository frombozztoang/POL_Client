package com.umc.pieciesoflife.DTO.UserDto

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("userId") val userId: Int,
    @SerializedName("level") val level: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profileImgUrl") val profileImgUrl: String,
    @SerializedName("score") val score: Int
)