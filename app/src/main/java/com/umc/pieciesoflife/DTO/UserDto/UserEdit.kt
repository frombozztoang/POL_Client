package com.umc.pieciesoflife.DTO.UserDto

data class UserEdit(
    val `data`: UserEditData,
    val messsage: String,
    val status: Int,
    val success: Boolean
)