package com.example.enight.dataBase.foodTruck

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * this class is the data access object of the food truck table
 */
@Dao
interface FoodTruckDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodTruck : FoodTruck)

    /**
     * this method update a data into the database
     */
    @Update
    suspend fun update(foodTruck: FoodTruck)


    /**
     * this method get a specific data using location name from the database
     */
    @Query("Select * from foodTruck where location like :key")
    suspend fun getFoodTruck(key: String) : FoodTruck?

    /**
     * this method delete all data into database
     */
    @Query("delete from foodTruck")
    suspend fun clear()

    /**
     * this method get the last data from the database
     */
    @Query("select * from foodTruck order by location desc limit 1")
    suspend fun getToFoodTruck(): FoodTruck?

    /**
     * this method get all data from database
     */
    @Query("select * from foodTruck order by location asc")
    fun getAll(): LiveData<List<FoodTruck>>
}