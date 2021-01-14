package com.example.enight.view.foodTrucks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.foodTruck.FoodTruckDatabaseDao

class FoodTruckViewModelFactory(
    private val database: FoodTruckDatabaseDao,
    private val application: Application): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodTruckViewModel::class.java)) {
            return FoodTruckViewModel(database, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}