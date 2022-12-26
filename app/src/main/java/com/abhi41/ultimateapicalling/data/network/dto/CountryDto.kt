package com.abhi41.ultimateapicalling.data.network.dto


import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("countryname")
    val countryname: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("iso")
    val iso: String?,
    @SerializedName("iso3")
    val iso3: String?,
    @SerializedName("nicename")
    val nicename: String?,
    @SerializedName("numcode")
    val numcode: String?,
    @SerializedName("phonecode")
    val phonecode: String?
)