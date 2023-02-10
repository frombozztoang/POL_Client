package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryDetailExploreData(
    @SerializedName("questionList") @Expose val questionList: List<StoryDetailExploreQuestion>,
    @SerializedName("story") @Expose val story: StoryDetailExploreStory
)