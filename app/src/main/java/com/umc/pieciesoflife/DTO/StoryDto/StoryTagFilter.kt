package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryTagFilter( // 태그 필터링 조회(나의 자서전1)
    @SerializedName("dataList") @Expose val dataList: List<StoryTagFilterData>,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("status") @Expose val status: Int,
    @SerializedName("success") @Expose val success: Boolean
)