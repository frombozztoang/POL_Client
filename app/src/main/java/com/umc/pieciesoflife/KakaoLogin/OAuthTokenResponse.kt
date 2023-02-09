package com.umc.pieciesoflife.KakaoLogin

import com.google.gson.annotations.SerializedName

data class OAuthTokenResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>
) {
    data class Data (
        @SerializedName("accessToken") val accessToken: String,
    )

}
