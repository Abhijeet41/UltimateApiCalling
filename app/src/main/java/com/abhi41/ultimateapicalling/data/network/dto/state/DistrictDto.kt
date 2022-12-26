package com.abhi41.ultimateapicalling.data.network.dto.state


import com.google.gson.annotations.SerializedName

data class DistrictDto(
    @SerializedName("district")
    val district: List<District>
)