package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.QuestionDto.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface QuestionService {
    @GET("/question/{tagId}")
    @Headers("content-type: application/json",
    "Authorization: Token")
    fun getQuestion(
        @Path("tagId") tagId : Long
    ) : Call<Question>
}