package com.example.enight.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.enight.network.FoodApi
import com.example.enight.network.FoodTruck
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class Repository(private val database: FoodTruckDatabase) {

    suspend fun refreshFoodTruck() {
        withContext(Dispatchers.IO) {
            val foodTruckRefreshList = getFoodFromNetwork()
            database.FoodTruckDao.insertAll(foodTruckRefreshList.asDatabaseModel())
        }
    }

    val foodTruck: LiveData<List<FoodTruckModel>> = Transformations.map(database.FoodTruckDao.getFoodTruck()){
        it.asDomainModel()
    }
}

fun getFoodFromNetwork(): NetworkContainer{

    val resp = NetworkContainer(ArrayList<NetworkFoodTruck>())

    try {
        val result = FoodApi.retrofitService.getProperties()
        result.enqueue(
            object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    if(response.isSuccessful && response.body()!=null){
                        val user_array: JsonArray = response.body()!!.getAsJsonArray("records")
                        for(value in user_array){
                            makeFoodList(value)
                        }
                        resp.foodTrucks = list
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    //_foodTrucks.value = " fail fail fail ${t.message} "
                }
            }
        )
    }
    catch (e: Exception){

        //_foodTrucks.value = "Fail ${e.message}"
    }
    return resp
}

var list = ArrayList<NetworkFoodTruck>()

private fun makeFoodList(value : JsonElement){
    val location = value.asJsonObject.get("fields").asJsonObject.get("emplacement")
    val geometryAtl = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[0]
    val geometryLog = value.asJsonObject.get("geometry").asJsonObject.get("coordinates").asJsonArray[1]
    val newFood = NetworkFoodTruck(location.toString(),geometryAtl.asInt,geometryLog.asInt)
    list.add(newFood)
}