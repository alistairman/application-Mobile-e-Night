package com.example.enight.dataBase.cour

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * this is all possible query done on the courses table
 * this interface make the link between the app and the table of courses
 */
@Dao
interface CourDatabaseDao {
    /**
     * this method insert a new course into the courses table
     */
    @Insert
    suspend fun insert(cour: Cour)

    /**
     * this method update a course into course table
     */
    @Update
    suspend fun update(cour: Cour)


    /**
     * this method get a specific course by course's name from the courses table
     */
    @Query("Select * from cour where courId like :key")
    suspend fun getCour(key: String) : Cour?

    /**
     * this method delete all courses from table
     */
    @Query("delete from cour")
    suspend fun clear()

    /**
     * this method get the last course from the table
     */
    @Query("select * from cour order by courId desc limit 1")
    suspend fun getToCour(): Cour?

    /**
     * this method get all data from table
     */
    @Query("select * from cour order by courId desc")
    fun getAll(): LiveData<List<Cour>>


    @Query("SELECT * from cour WHERE courId = :key")
    fun getCourWithId(key: String): LiveData<Cour>

}