package com.example.enight.view.cour

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.cour.CourDatabaseDao

/**
 * this is the factory of the view model of the course fragment
 * this class create view model with specific argument the view model needed
 */
class CourViewModelFactory (
    private val database : CourDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourViewModel::class.java)) {
            return CourViewModel(database, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}