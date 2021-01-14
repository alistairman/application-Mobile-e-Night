package com.example.enight.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import com.example.enight.dataBase.email.Email
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao

/**
 * this class represent the database of the app with different tables
 */
@Database(entities = [Email::class,Profile::class,Cour::class], version = 6, exportSchema = false)
abstract class EnightDB : RoomDatabase() {

    /**
     * this variable is used to access to login table
     */
    abstract val emailDatabaseDao : EmailDatabaseDao

    /**
     * this variable is used to access to profile table
     */
    abstract val profileDatabaseDao : ProfileDatabaseDao

    /**
     * this variable is used to access to courses table
     */
    abstract val courDatabaseDao : CourDatabaseDao

    /**
     * this companion create and make the connexion to database
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