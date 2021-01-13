package com.example.enight.view.foodTrucks

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.cache.Repository
import com.example.enight.cache.getDatabase
import com.example.enight.network.FoodApi
import com.example.enight.network.FoodTruck
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class FoodTruksViewModel(application: Application) : AndroidViewModel(application) {

    private val _foodTrucks = MutableLiveData<String>()
    val foodTrucks : LiveData<String>
        get() = _foodTrucks

    private val _foodTrucksList = MutableLiveData<List<FoodTruck>>()
    val foodTrucksList: LiveData<List<FoodTruck>>
        get() = _foodTrucksList

    var list = ArrayList<FoodTruck>()

    private val repository = Repository(getDatabase(application))

    val playlist = repository.foodTruck


    init {
        //getFoodTrucks()
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshFoodTruck()

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(playlist.value.isNullOrEmpty())
                    _foodTrucks.value = "error avec ${networkError.message}"
            }
        }
    }

    /**private fun refreshDataFromNetwork() = viewModelScope.launch {

        try {
            ...
        } catch (networkError: IOException) {
            // Show a Toast error message and hide the progress bar.
        }
    }*/

   /**private fun getFoodTrucks(){
        viewModelScope.launch {
            try {
                val result = FoodApi.retrofitService.getProperties()
                result.enqueue(
                    object : Callback<JsonObject> {
                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {
                            if(response.isSuccessful() && response.body()!=null){
                             val user_array: JsonArray = response.body()!!.getAsJsonArray("records")
                            _foodTrucks.value = " ${user_array.size()} Food Trucks trouvés "
                            for (value in user_array ){
                                    makeFoodList(value)
                                }
                                _foodTrucksList.value = list
                            }
                        }

                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            _foodTrucks.value = " fail fail fail ${t.message} "
                        }
                    }
                )
            }
            catch (e: Exception){

                _foodTrucks.value = "Fail ${e.message}"
            }
        }
    }*/

    private fun makeFoodList(value : JsonElement){
        val location = value.asJsonObject.get("fields").asJsonObject.get("emplacement")
        val geometryAtl = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[0]
        val geometryLog = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[1]
        val newFood = FoodTruck(location.toString(),geometryAtl.asInt,geometryLog.asInt)
        list.add(newFood)
    }
}