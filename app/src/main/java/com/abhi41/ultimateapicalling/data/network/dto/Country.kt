package com.abhi41.ultimateapicalling.data.network.dto


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country")
    val country: List<CountryDto>?
)