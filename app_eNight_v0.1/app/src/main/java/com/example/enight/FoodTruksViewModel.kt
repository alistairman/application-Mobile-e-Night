package com.example.enight

//import com.example.enight.network.retrofit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enight.network.FoodApi
import com.example.enight.network.FoodTruck
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodTruksViewModel : ViewModel() {

    private val _foodTrucks = MutableLiveData<String>()
    val foodTrucks : LiveData<String>
        get() = _foodTrucks

    init {
        getFoodTrucks()
    }

    private fun getFoodTrucks(){
        viewModelScope.launch {
            try {
                val result = FoodApi.retrofitService.getProperties()
                result.enqueue(
                    /**object : Callback<FoodTruck>{
                        override fun onResponse(
                            call: Call<FoodTruck>,
                            response: Response<FoodTruck>
                        ) {
                            val name = response.body()?.nbEntrées
                            //val user_array: JsonArray = response.body()!!.getAsJsonArray("records")
                            _foodTrucks.value = " success ${name} lignes trouvé "
                        }

                        override fun onFailure(call: Call<FoodTruck>, t: Throwable) {
                            _foodTrucks.value = " fail fail fail ${t.message} "
                        }

                    }*/
                    object : Callback<JsonObject> {
                        override fun onResponse(
                            call: Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {
                            val name = response.body()!!.get("nhits")
                            val user_array: JsonArray = response.body()!!.getAsJsonArray("records")
                            _foodTrucks.value = " success ${name} lignes trouvé "
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
    }
}