package com.example.enight.cache

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FoodTruckDao {

    @Query("select * from databaseFoodTruck")
    fun getFoodTruck(): LiveData<List<DatabaseFoodTruck>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( foodTruck: List<DatabaseFoodTruck>)
}

@Database(entities = [DatabaseFoodTruck::class], version = 2,exportSchema = false)
abstract class FoodTruckDatabase: RoomDatabase() {
    abstract val FoodTruckDao: FoodTruckDao
}

private lateinit var INSTANCE: FoodTruckDatabase

fun getDatabase(context: Context): FoodTruckDatabase {
    synchronized(FoodTruckDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FoodTruckDatabase::class.java,
                "foodTruck").build()
        }
    }
    return INSTANCE
}