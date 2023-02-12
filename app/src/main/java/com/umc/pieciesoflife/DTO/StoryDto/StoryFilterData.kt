package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilterData(
    val stories: List<StoryFilterStory>,
    val storyTag: String
)