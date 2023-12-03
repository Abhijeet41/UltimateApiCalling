package com.abhi41.ultimateapicalling

import af.airpay.dukasdk.model.Transaction
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.abhi41.ultimateapicalling.adapters.CountryAdapter
import com.abhi41.ultimateapicalling.adapters.StateAdapter
import com.abhi41.ultimateapicalling.databinding.ActivityMainBinding
import com.abhi41.ultimateapicalling.model.CountryModel
import com.abhi41.ultimateapicalling.model.StateModel
import com.abhi41.ultimateapicalling.presentation.MainViewModel
import com.abhi41.ultimateapicalling.utils.NetworkResult
import com.abhi41.ultimateapicalling.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    val listOfCountry = mutableListOf<CountryModel>()
    val listOfState = mutableListOf<StateModel>()
    val listOfDistrict = mutableListOf<String?>()
    var activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            try {
                val response = intent?.getStringExtra("response")
                Log.d(TAG, "response: $response")
                val jsonObject = JSONObject(response!!)
                val status = jsonObject.getString("status_code")
                Log.d(TAG, "response: $response")
                val errorMsg = jsonObject.getString("status_msg")
                Log.d(TAG, "status_msg: $errorMsg")
                Toast.makeText(applicationContext,status,Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observers()
        onItemSelected()

        Transaction.Builder(this@MainActivity, activityResultLauncher)
            .setMidNo("247033")
            .setAmount("2000")
            .setSerialNo("82389399")
            .setOrderId("DUKA${Util.generateRandom6DigitNumber()}")
            .build()
            .getTransaction()

       /* val spnCountryAdapter = CountryAdapter(applicationContext, listOfCountry)
        binding.txtCountry.setAdapter(spnCountryAdapter)
        // binding.txtCountry.setText("INDIA")

        val spnStateAdapter = StateAdapter(applicationContext, listOfState)
        binding.txtState.setAdapter(spnStateAdapter)

        val spnDistrictAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfDistrict)
        spnDistrictAdapter.setDropDownViewResource(R.layout.single_country_name)
        binding.txtCity.setAdapter(spnDistrictAdapter)*/
    }

    private fun onItemSelected() {
        binding.txtCountry.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedItem = parent.adapter.getItem(position)
                if (selectedItem != null) {
                    binding.txtCountry.setText(listOfCountry.get(position).countryname)
                    mainViewModel.mutableCountry.value = listOfCountry.get(position).id.toString()
                    lifecycleScope.launchWhenStarted {
                        mainViewModel.requestStates()
                    }
                }
            }

        binding.txtState.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedItem = parent.adapter.getItem(position)
                if (selectedItem != null) {
                    binding.txtState.setText(listOfState.get(position).statename)
                    mainViewModel.mutableStateId.value = listOfState.get(position).stateid.toString()
                    lifecycleScope.launchWhenStarted {
                        mainViewModel.requestDistrict()
                    }
                }
            }
    }

    private fun observers() {
        mainViewModel.countryResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    listOfCountry.clear()
                    response.data?.forEach { country ->
                        Log.d(TAG, "country: ${country.countryname}")
                        listOfCountry.add(
                            CountryModel(
                                countryname = country.countryname,
                                id = country.id
                            )
                        )
                    }
                    //select india by default
                    val countryName =
                        listOfCountry.filter { c -> c.countryname == "INDIA" }.single()
                    binding.txtCountry.setText(countryName.countryname)

                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        applicationContext,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }

        mainViewModel.stateResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    listOfState.clear()
                    response.data?.forEach { state ->
                        Log.d(TAG, "State: ${state.statename}")
                        listOfState.add(StateModel(stateid = state.stateid, statename = state.statename))
                    }
                    //select maharastra by default

                   val state = listOfState.filter { s-> s.statename == "Maharashtra" }.single()
                    binding.txtState.setText(state.statename)

                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        applicationContext,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }

        mainViewModel.districtResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    listOfDistrict.clear()
                    response.data?.forEach { district ->
                        listOfDistrict.add(district.districtname)
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        applicationContext,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }


}