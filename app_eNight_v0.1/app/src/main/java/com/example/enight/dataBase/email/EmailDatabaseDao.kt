package com.example.enight.dataBase.email

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.enight.dataBase.email.Email

/**
 * this class is the dao of the data base
 */
@Dao
interface EmailDatabaseDao {

    /**
     * this method insert a data into the database
     */
    @Insert
    suspend fun insert(mail : Email)

    /**
     * this method update a data into the database
     */
    @Update
    suspend fun update(mail: Email)

    /**
     * this method get a specific data by id from the database
     */
    @Query("Select * from login_mail where mailId = :key")
    suspend fun get(key: Long) : Email?

    /**
     * this method get a specific data by value of mail from the database
     */
    @Query("Select * from login_mail where log_mail like :key")
    suspend fun getMail(key: String) : Email?

    /**
     * this method delete all data into database
     */
    @Query("delete from login_mail")
    suspend fun clear()

    /**
     * this method get the last data from the database
     */
    @Query("select * from login_mail order by mailId desc limit 1")
    suspend fun getToMail(): Email?

    /**
     * this method get all data from database
     */
    @Query("select * from login_mail order by mailId desc")
    fun getAll(): LiveData<List<Email>>
}