package com.umc.pieciesoflife.Retrofit

import com.umc.pieciesoflife.Interface.StoryService
import com.umc.pieciesoflife.KakaoLogin.OAuthTokenService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL = "http://3.39.155.109:8080"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Interface 클래스 파일들 추가
    val service: StoryService = retrofit.create(StoryService::class.java)
    val TokenService: OAuthTokenService = retrofit.create(OAuthTokenService::class.java)
}