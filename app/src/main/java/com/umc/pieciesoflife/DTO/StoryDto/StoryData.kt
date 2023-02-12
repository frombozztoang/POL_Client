package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.SerializedName

data class StoryData(
    @SerializedName("color") val color: String,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("likeCnt") val likeCnt: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("profileImgUrl") val profileImgUrl: String,
    @SerializedName("title") val title: String
)