package com.umc.pieciesoflife.DTO.UserDto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data") val `data`: UserData,
    @SerializedName("error") val error: Any,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean
)