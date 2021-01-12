package com.example.enight.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

class FoodTruck (
    val nbEntrées : LiveData<Int>,
    val parameters : LiveData<Int>
        ){

    @SerializedName("nhits")
    @Expose
    private var _nbEntrées = MutableLiveData<Int>()
        get() = _nbEntrées

    @SerializedName("parameters")
    @Expose
    private var _parameters = MutableLiveData<Int>()
        get() = _parameters
}