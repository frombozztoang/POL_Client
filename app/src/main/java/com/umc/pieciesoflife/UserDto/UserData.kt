package com.umc.pieciesoflife.UserDto

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("level") val level: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profileImgUrl") val profileImgUrl: String,
    @SerializedName("score") val score: Int
)