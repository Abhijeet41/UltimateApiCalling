package com.abhi41.ultimateapicalling.data.network.dto.state


import com.google.gson.annotations.SerializedName

data class District(
    @SerializedName("districtid")
    val districtid: Int?,
    @SerializedName("districtname")
    val districtname: String?,
    @SerializedName("stateid")
    val stateid: Int?
)