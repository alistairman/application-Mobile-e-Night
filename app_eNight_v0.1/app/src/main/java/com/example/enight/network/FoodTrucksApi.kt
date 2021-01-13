package com.example.enight.network

import com.example.enight.cache.NetworkContainer
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private  const val BASE_URL = "https://opendata.brussels.be/api/records/1.0/search/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FoodApiService {
    @GET("?dataset=bxl_food_trucks")
    fun getProperties() : Call<JsonObject>
    //suspend fun getProperties() : NetworkContainer
}


object FoodApi {
    val retrofitService : FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java) }
}