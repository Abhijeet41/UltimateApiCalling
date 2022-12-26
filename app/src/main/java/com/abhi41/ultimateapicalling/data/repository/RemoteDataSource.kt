package com.abhi41.ultimateapicalling.data.repository

import com.abhi41.ultimateapicalling.data.network.CountryAPi
import com.abhi41.ultimateapicalling.data.network.dto.Country
import com.abhi41.ultimateapicalling.data.network.dto.State
import com.abhi41.ultimateapicalling.data.network.dto.state.DistrictDto
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiCountryAPi: CountryAPi
) {

    suspend fun getCountries(): Response<Country> {
        return apiCountryAPi.getCountries()
    }

    suspend fun getStates(query: Map<String, String>): Response<State> {
        return apiCountryAPi.getStates(query)
    }

    suspend fun getDistrict(stateId: String?): Response<DistrictDto> {
        return apiCountryAPi.getDistrict(stateId)
    }
}