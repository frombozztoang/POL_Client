package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StoryPost(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("color") val color: String,
    @SerializedName("storyTagList") val storyTagList: Serializable?,
    @SerializedName("qnaList") val qnaList: Serializable?
)