package com.abhi41.ultimateapicalling.data.network.dto


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("state")
    val state: List<StateDto>?
)