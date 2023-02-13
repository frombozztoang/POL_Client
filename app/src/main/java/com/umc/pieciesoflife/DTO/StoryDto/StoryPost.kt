package com.umc.pieciesoflife.DTO.StoryDto

import java.io.Serializable

data class StoryPost(
    val title: String,
    val color: String,
    val description: String,
    val qnaList: List<StoryQna>,
    val storyTagList: List<StoryTag>
):Serializable
