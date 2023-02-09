package com.umc.pieciesoflife.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
 //   @SerializedName("questionTemplate")
 //   @Expose
 //   val questionTemplate: String //가져올 질문 내용

    @SerializedName("status") var status: Int? = null, //상태
    @SerializedName("success") var success: Int? = null, //성공시
    @SerializedName("message") var message: String? = null, //메시지
    @SerializedName("data") var data: Data? = Data()

)
/*
    {
        data class Data(
            @SerializedName("questionTemplate") val questionTemplate: String //가져올 질문 내용
        )
    }
)
 */


