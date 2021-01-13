package com.example.enight.cache

import com.squareup.moshi.JsonClass




@JsonClass(generateAdapter = true)
data class NetworkContainer(var foodTrucks: List<NetworkFoodTruck>)

/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkFoodTruck(
    val location :String,
    val geometryAlt: Int,
    val geometryLog: Int)

fun NetworkContainer.asDatabaseModel(): List<DatabaseFoodTruck> {
    return foodTrucks.map {
        DatabaseFoodTruck(
            location = it.location,
            geometryAlt = it.geometryAlt,
            geometryLog = it.geometryLog)
    }
}