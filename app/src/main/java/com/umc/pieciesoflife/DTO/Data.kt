package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("questionTemplate") val questionTemplate: String?=null //가져올 질문 내용
)
