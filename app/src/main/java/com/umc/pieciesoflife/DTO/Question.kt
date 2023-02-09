package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("questionTemplate")
    @Expose
    val questionTemplate: String, //가져올 질문 내용
)
/*
    @SerializedName("status") val status: Int, //상태
    @SerializedName("success") val success: Int, //성공시
    @SerializedName("message") val message: String, //메시지
)

data class Data(
        @SerializedName("questionTemplate") val questionTemplate: String, //가져올 질문 내용
    )

data class Error (
    @SerializedName("code") val code: String,
)*/