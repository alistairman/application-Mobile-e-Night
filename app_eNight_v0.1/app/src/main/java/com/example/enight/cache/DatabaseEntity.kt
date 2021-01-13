package com.example.enight.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseFoodTruck(
    @PrimaryKey
    val location : String,
    val geometryAlt: Int,
    val geometryLog: Int
)
    fun List<DatabaseFoodTruck>.asDomainModel(): List<FoodTruckModel> {
        return map {
            FoodTruckModel(
                location = it.location,
                geometryAlt = it.geometryAlt,
                geometryLog = it.geometryLog)
        }
    }
