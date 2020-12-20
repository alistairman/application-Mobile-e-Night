package com.example.enight.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enight.dataBase.email.Email
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.users.ProfileDatabaseDao
import com.example.enight.dataBase.users.Profiles

/**
 * this class represent the connection to the database
 */
@Database(entities = [Email::class,Profiles::class], version = 3, exportSchema = false)
abstract class Enight_Database : RoomDatabase() {

    /**
     * this variable represent the database
     */
    abstract val emailDatabaseDao : EmailDatabaseDao

    abstract val profileDatabaseDao : ProfileDatabaseDao

    //abstract val categoryDatabaseDao : ProfileDatabaseDao

    /**
     * this companion make the connexion to database
     */
    companion object{
        @Volatile
        private var INSTANCE : Enight_Database? = null

        fun getInstance(context : Context) : Enight_Database {
            synchronized(this){
                var instance = INSTANCE

                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Enight_Database::class.java,
                        "Enight_dataBase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}