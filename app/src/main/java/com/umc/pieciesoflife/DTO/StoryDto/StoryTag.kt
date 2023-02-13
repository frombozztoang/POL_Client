package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.SerializedName

data class StoryTag(
    @SerializedName("tagId") val tagId: Int,
    @SerializedName("tagContent") val tagContent: String
)