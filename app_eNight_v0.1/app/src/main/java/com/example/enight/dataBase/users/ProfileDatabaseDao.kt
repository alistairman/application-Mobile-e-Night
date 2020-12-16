package com.example.enight.dataBase.users

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProfileDatabaseDao {
        /**
         * this method insert a data into the database
         */
        @Insert
        suspend fun insert(profile : Profiles)

        /**
         * this method update a data into the database
         */
        @Update
        suspend fun update(profile: Profiles)

        /**
         * this method get a specific data by id from the database
         */
        @Query("Select * from profile where userId = :key")
        suspend fun get(key: Long) : Profiles?

        /**
         * this method get a specific data by value of mail from the database
         */
        @Query("Select * from profile where email like :key")
        suspend fun getProfile(key: String) : Profiles?

        /**
         * this method delete all data into database
         */
        @Query("delete from profile")
        suspend fun clear()

        /**
         * this method get the last data from the database
         */
        @Query("select * from profile order by userId desc limit 1")
        suspend fun getToProfile(): Profiles?

        /**
         * this method get all data from database
         */
        @Query("select * from profile order by userId desc")
        fun getAll(): LiveData<List<Profiles>>
}