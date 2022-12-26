package com.abhi41.ultimateapicalling.data.network

import com.abhi41.ultimateapicalling.data.network.dto.Country
import com.abhi41.ultimateapicalling.data.network.dto.State
import com.abhi41.ultimateapicalling.data.network.dto.state.DistrictDto
import retrofit2.Response
import retrofit2.http.*

interface CountryAPi {


    @GET("/countrylist/")
    suspend fun getCountries(): Response<Country>
    
    @POST("/statelist/")
    @FormUrlEncoded
    suspend fun getStates(@FieldMap query: Map<String, String>): Response<State>

    @POST("/districtlist/")
    @FormUrlEncoded
    suspend fun getDistrict( @Field("stateid") stateId: String?):Response<DistrictDto>


}