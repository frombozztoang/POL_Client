package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryDetailExploreStory(
    @SerializedName("backgroundColor") @Expose val backgroundColor: String,
    @SerializedName("date") @Expose val date: String,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("isLiked") @Expose val isLiked: Boolean,
    @SerializedName("likeNum") @Expose val likeNum: Int,
    @SerializedName("nickname") @Expose val nickname: String,
    @SerializedName("profileImgUrl") @Expose val profileImgUrl: String,
    @SerializedName("title") @Expose val title: String
)