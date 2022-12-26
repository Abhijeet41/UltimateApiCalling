package com.abhi41.ultimateapicalling.data.network.dto


import com.google.gson.annotations.SerializedName

data class StateDto(
    @SerializedName("countryid")
    val countryid: Int?,
    @SerializedName("stateid")
    val stateid: Int?,
    @SerializedName("statename")
    val statename: String?
)