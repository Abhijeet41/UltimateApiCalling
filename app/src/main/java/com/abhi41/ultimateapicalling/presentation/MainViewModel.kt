package com.abhi41.ultimateapicalling.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi41.ultimateapicalling.data.network.dto.Country
import com.abhi41.ultimateapicalling.data.network.dto.CountryDto
import com.abhi41.ultimateapicalling.data.network.dto.State
import com.abhi41.ultimateapicalling.data.network.dto.StateDto
import com.abhi41.ultimateapicalling.data.network.dto.state.District
import com.abhi41.ultimateapicalling.data.network.dto.state.DistrictDto
import com.abhi41.ultimateapicalling.data.repository.RemoteDataSource
import com.abhi41.ultimateapicalling.utils.Constants.DEFAULT_COUNTRY_ID
import com.abhi41.ultimateapicalling.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RemoteDataSource
) : ViewModel() {
    /**  RETROFIT */
    val countryResponse: MutableLiveData<NetworkResult<List<CountryDto>>> = MutableLiveData()
    val stateResponse: MutableLiveData<NetworkResult<List<StateDto>>> = MutableLiveData()
    val districtResponse: MutableLiveData<NetworkResult<List<District>>> = MutableLiveData()

    val mutableCountry: MutableLiveData<String> = MutableLiveData()
    val mutableStateId: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            val resultCountry = async { requestCountries() }
            val resultState = async { requestStates() }
            val resultDistrict = async { requestDistrict() }

            resultCountry.await()
            resultState.await()
            resultDistrict.await()
        }
    }

    private suspend fun requestCountries() {
        countryResponse.value = NetworkResult.Loading()
        try {
            val response = repository.getCountries()
            countryResponse.value = handleCountryResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            countryResponse.value = NetworkResult.Error("Countries not found")
        }
    }

    suspend fun requestStates() {
        stateResponse.value = NetworkResult.Loading()
        try {
            val response = repository.getStates(applyStateQuery())
            stateResponse.value = handleStateResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            stateResponse.value = NetworkResult.Error("States not found")
        }
    }

    suspend fun requestDistrict() {
        districtResponse.value = NetworkResult.Loading()
        try {
            val response = repository.getDistrict(mutableStateId.value ?: "22")
            districtResponse.value = handleDistrictResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            districtResponse.value = NetworkResult.Error("District not found")
        }
    }

    private fun handleCountryResponse(response: Response<Country>):
            NetworkResult<List<CountryDto>>? {

        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Connection Timeout!")
            }

            response.isSuccessful -> {
                val response = response.body()
                val countries = response?.country
                if (countries != null)
                    return NetworkResult.Success(countries)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
        return null
    }

    private fun handleStateResponse(response: Response<State>): NetworkResult<List<StateDto>>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Connection Timeout!")
            }

            response.isSuccessful -> {
                val response = response.body()
                val states = response?.state
                if (states != null)
                    return NetworkResult.Success(states)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
        return null
    }

    private fun handleDistrictResponse(response: Response<DistrictDto>): NetworkResult<List<District>>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Connection Timeout!")
            }

            response.isSuccessful -> {
                val response = response.body()
                val district = response?.district
                if (district != null)
                    return NetworkResult.Success(district)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
        return null
    }

    fun applyStateQuery(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["countryid"] = mutableCountry.value ?: DEFAULT_COUNTRY_ID
        return queries
    }
}