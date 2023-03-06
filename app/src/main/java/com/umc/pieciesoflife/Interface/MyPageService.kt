package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.MyPageDto.MyPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageService {
    @GET("user/mypage")
    fun getMyPage(
        @Header("Authorization") accessToken: String
    ): Call<MyPage>
}