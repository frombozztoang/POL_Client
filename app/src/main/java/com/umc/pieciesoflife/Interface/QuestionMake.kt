package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface QuestionMake {

    @Headers("Content-type: application/json", "Authorization: Bearer <token>")

    @GET("/question/{tagId}")
    fun request() : Call<Question>

    @GET("/question/1")
    fun response(
        //@Path("tagId") tagId : Int
    ) : Call<Question>
}