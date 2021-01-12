package com.example.enight.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class FoodTruck (
    val location : String,
    val geometryAlt: Int,
    val geometryLog: Int
    )