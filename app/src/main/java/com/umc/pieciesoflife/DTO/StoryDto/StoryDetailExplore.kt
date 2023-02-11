package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryDetailExplore( // 이야기 자세히보기 (둘러보기, 나의이야기 둘다 같은 api 호출)
    @SerializedName("data") @Expose val `data`: StoryDetailExploreData,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("status") @Expose val status: Int,
    @SerializedName("success") @Expose val success: Boolean
)