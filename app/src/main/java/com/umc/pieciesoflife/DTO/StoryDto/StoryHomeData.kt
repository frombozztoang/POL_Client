package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryHomeData(
    @SerializedName("storyList") @Expose val storyList: List<StoryHomeStory>
)