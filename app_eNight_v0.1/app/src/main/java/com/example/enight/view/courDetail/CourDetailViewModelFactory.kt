package com.example.enight.view.courDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.cour.CourDatabaseDao

/**
 * this is the factory of the course detail view model
 * he receive the id of the course clicked and the database
 */
class CourDetailViewModelFactory (
    private val courId: String,
    private val database: CourDatabaseDao,
    private  val application: Application
): ViewModelProvider.Factory{

        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CourDetailViewModel::class.java)) {
                return CourDetailViewModel(courId ,database,application ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }