package com.umc.pieciesoflife.DTO.StoryDto

import java.io.Serializable

data class StoryPost(
    val color: String,
    val title: String,
    val description: String,
    val qnaList: Serializable?,
    val storyTagList: Serializable?
)