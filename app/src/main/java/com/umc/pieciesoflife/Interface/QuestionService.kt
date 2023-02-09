package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.Question
import com.umc.pieciesoflife.DataClass.ChatMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestionService {
    @GET("/question/{tagId}")
    @Headers("content-type: application/json",
    "Authorization: Token")
    fun getQuestion(
        @Path("tagId") tagId : Long
    ) : Call<String>
}