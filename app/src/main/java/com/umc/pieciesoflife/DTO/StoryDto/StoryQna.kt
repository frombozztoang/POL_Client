package com.umc.pieciesoflife.DTO.StoryDto

import java.io.Serializable


data class StoryQna(
    val answer: String,
    val question: String,
    val tagId: Int
):Serializable