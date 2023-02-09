package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Response
data class PostData(
    @SerializedName("score")
    @Expose
    val score: Int,

    @SerializedName("level")
    @Expose
    val level: Int,
)

//Request Body
data class TagPost(
    @SerializedName("tagId")
    @Expose
    val tagId: Int,

    @SerializedName("tagContent")
    @Expose
    val tagContent: String,
)

data class Story(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("color")
    @Expose
    val color: String,
)

data class QnA(
    @SerializedName("question")
    @Expose
    val question: String,

    @SerializedName("answer")
    @Expose
    val answer: String,

    @SerializedName("tagId")
    @Expose
    val tagId: String,
)
