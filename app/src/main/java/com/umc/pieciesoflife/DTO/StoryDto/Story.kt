package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("dataList") val dataList: List<StoryData>,
    @SerializedName("messsage") val messsage: String,
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean
)