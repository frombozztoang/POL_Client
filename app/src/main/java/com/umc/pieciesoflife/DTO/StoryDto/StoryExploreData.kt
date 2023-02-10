package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryExploreData(
    @SerializedName("storyList") @Expose val storyList: List<StoryExploreStory>
)