package com.umc.pieciesoflife.DTO.StoryDto

data class StoryFilterDataTag(
    val stories: List<StoryFilterDataTagStory>,
    val storyTag: String
)