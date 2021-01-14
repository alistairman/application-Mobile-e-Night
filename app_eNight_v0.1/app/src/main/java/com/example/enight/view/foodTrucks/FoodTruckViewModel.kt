package com.example.enight.view.foodTrucks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.foodTruck.FoodTruck
import com.example.enight.dataBase.foodTruck.FoodTruckDatabaseDao
import com.example.enight.network.FoodApi
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 *this is the view model of the food truck fragment
 */
class FoodTruckViewModel(
    private val database: FoodTruckDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    /**
     * this variable is used to represent the number of food truck found
     */
    private val _foodTrucksNb = MutableLiveData<String>()
    val foodTrucksNb : LiveData<String>
        get() = _foodTrucksNb


    /**
     * this variable is used to fill the recycle view with diff food truck
     * this variable represent a list of food trucks found
     */
    var foodTruckList = database.getAll()

    //val _navigateToMap = MutableLiveData<String>()

    private val _findFoodTruck = MutableLiveData<String>()
    val findFoodTruck
        get() = _findFoodTruck

    /**
     * this method initialize the view model by calling get Food Trucks method
     */
    init {
        getFoodTrucks()
    }


    /**
     * this method call the api method to get food trucks
     * if the call success then we get the number of the food truck found
     * then for each value we call the make foodList method to get only specific value of food truck
     * then add food truck into the food truck list variable
     * if call not success then show the error message
     */
   private fun getFoodTrucks(){
        viewModelScope.launch {
            try {
                delay(3000)
                val result = FoodApi.retrofitService.getProperties()
                result.enqueue(
                    object : Callback<JsonObject> {
                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {
                            if(response.isSuccessful && response.body()!=null){
                             val array: JsonArray = response.body()!!.getAsJsonArray("records")
                            _foodTrucksNb.value = " ${array.size()} Food Trucks trouvés "
                            for (value in array ){
                                    makeFoodList(value)
                                }
                            }
                        }
                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            _foodTrucksNb.value = " fail fail fail ${t.message} "
                        }
                    }
                )
            }
            catch (e: Exception){
                delay(3000)
                _foodTrucksNb.value = "Fail ${e.message}"
            }
        }
    }

    /**
     * this method is call to create a new instance of food truck with specific value of food truck
     * and add him in the list variable
     */
    private fun makeFoodList(value : JsonElement){
        val location = value.asJsonObject.get("fields").asJsonObject.get("emplacement")
        val geometryAtl = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[0]
        val geometryLog = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[1]
        val newFood = FoodTruck(location.toString(),geometryAtl.asFloat,geometryLog.asFloat)
        viewModelScope.launch {
            database.insert(newFood)
        }
    }

    fun onFoodTruckClicked(foodTruckLocation: String) {
        _findFoodTruck.value = foodTruckLocation
    }

    fun onMapNavigated() {
        _findFoodTruck.value = null
    }
}

