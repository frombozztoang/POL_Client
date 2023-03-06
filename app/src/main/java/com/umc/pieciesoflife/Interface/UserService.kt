package com.umc.pieciesoflife.Interface


import android.text.Editable
import com.umc.pieciesoflife.DTO.UserDto.User
import com.umc.pieciesoflife.DTO.UserDto.UserEdit
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    // user 정보 조회
    @GET("user/info")
    fun getUserInfo(
        @Header("Authorization") accessToken: String
    ): Call<User>

    // user 정보 수정
    @Multipart
    @PATCH("user/profile")
    fun patchUserProfile(
        @Header("content-type") contentType: String, //multipart/form-data
        @Header("Authorization") accessToken: String, // jwt
        @Part("nickname") nickname: String,
        @Part profileImgUrl: MultipartBody.Part?
    ): Call<UserEdit>
}