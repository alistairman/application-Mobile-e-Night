package com.example.enight.view.users

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.email.EmailDatabaseDao

class UsersViewModelFactory(
    private val database: EmailDatabaseDao,
    private val application: Application): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(database , application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}