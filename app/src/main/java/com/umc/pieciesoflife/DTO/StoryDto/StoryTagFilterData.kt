package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryTagFilterData(
    @SerializedName("stories") @Expose val stories: List<StoryTagFilterStory>,
    @SerializedName("tag") @Expose val tag: String
)