package com.example.enight.dataBase.foodTruck

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this is the food truck table
 */
@Entity(tableName = "foodTruck")
data class FoodTruck (


    /**
     * the location of a food truck
     */
    @PrimaryKey
    @ColumnInfo(name = "location")
    val location : String,

    /**
     * the altitude of the food truck
     */
    @ColumnInfo(name = "altitude")
    val geometryAlt: Float = 0F,

    /**
     * the longitude of the food truck
     */
    @ColumnInfo(name = "longitude")
    val geometryLog: Float = 0F
    )