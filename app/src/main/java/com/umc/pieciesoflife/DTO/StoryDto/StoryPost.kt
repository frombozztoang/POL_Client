package com.umc.pieciesoflife.DTO.StoryDto

data class StoryPost(
    val color: String,
    val description: String,
    val qnaList: List<StoryQna>,
    val storyTagList: List<StoryTag>,
    val title: String
)