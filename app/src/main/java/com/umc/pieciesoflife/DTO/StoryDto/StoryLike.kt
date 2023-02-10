package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryLike(
    @SerializedName("message") @Expose val message: String,
    @SerializedName("status") @Expose val status: Int,
    @SerializedName("success") @Expose val success: Boolean
)