package com.example.enight.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enight.dataBase.email.Email
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao

/**
 * this class represent the connection to the database
 */
@Database(entities = [Email::class,Profile::class], version = 3, exportSchema = false)
abstract class EnightDB : RoomDatabase() {

    /**
     * this variable represent the database
     */
    abstract val emailDatabaseDao : EmailDatabaseDao

    abstract val profileDatabaseDao : ProfileDatabaseDao

    /**
     * this companion make the connexion to database
     */
    companion object{
        @Volatile
        private var INSTANCE : EnightDB? = null

        fun getInstance(context : Context) : EnightDB{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EnightDB::class.java,
                        "E-NightDB"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}