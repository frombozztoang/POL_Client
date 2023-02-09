package com.umc.pieciesoflife.KakaoLogin

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface OAuthTokenService {
    @POST("auth/kakao")
    fun postAuth (
        @Header("Authorization") accessToken: String?
    ): Call<OAuthTokenResponse>

}