package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryMain( // 대표 이야기로 설정
    @SerializedName("data") @Expose val `data`: StoryMainData,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("status") @Expose val status: Int,
    @SerializedName("success") @Expose val success: Boolean
)