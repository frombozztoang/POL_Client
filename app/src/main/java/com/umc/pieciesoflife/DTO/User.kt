package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("userInfoData") val data:Data
) {
    data class Data (
        @SerializedName("profileImgUrl") val profileImgUrl: String,
        @SerializedName("ninkname") val nickname: String,
        @SerializedName("score") val score: Int,
        @SerializedName("level") val level: Int,
        )
}
