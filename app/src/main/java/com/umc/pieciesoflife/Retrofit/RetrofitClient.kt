package com.umc.pieciesoflife.Retrofit

import com.umc.pieciesoflife.Interface.QuestionMake
import com.umc.pieciesoflife.Interface.StoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val URL = "http://3.39.155.109:8080"

    private val retrofit = Retrofit.Builder()

        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //Interface 클래스 파일들 추가
    val storyService: StoryService by lazy {retrofit.create(StoryService::class.java)}
    val questionMake: QuestionMake by lazy {retrofit.create(QuestionMake::class.java)}
}