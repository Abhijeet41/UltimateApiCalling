package com.abhi41.ultimateapicalling.data.network.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("expires_in")
    val expires_in: Int,
    @SerializedName("token_type")
    val token_type: String,
    @SerializedName("success")
    val success: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("scope")
    val scope: String
)
