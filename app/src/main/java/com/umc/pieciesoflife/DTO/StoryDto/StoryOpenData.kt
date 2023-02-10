package com.umc.pieciesoflife.DTO.StoryDto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryOpenData(
    @SerializedName("isOpened") @Expose val isOpened: Boolean
)