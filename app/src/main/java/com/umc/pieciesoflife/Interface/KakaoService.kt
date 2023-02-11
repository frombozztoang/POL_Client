package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.KakaoDto.Kakao
import retrofit2.Call
import retrofit2.http.*

interface KakaoService {
    @GET("auth/kakao")
    fun getAuth (
        @Header("accessToken") accessToken: String?,
    ): Call<Kakao>

}