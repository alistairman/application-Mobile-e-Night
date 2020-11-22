package com.example.enight.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * this class represent the connection to the database
 */
@Database(entities = [Email::class], version = 2, exportSchema = false)
abstract class EmailDatabase : RoomDatabase() {

    /**
     * this variable represent the database
     */
    abstract val emailDatabaseDao : EmailDatabaseDao

    /**
     * this companion make the connexion to database
     */
    companion object{
        @Volatile
        private var INSTANCE : EmailDatabase? = null

        fun getInstance(context : Context) : EmailDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmailDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}