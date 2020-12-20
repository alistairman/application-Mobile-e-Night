package com.example.enight.view.users

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.users.ProfileDatabaseDao
import com.example.enight.view.login.LoginViewModel

class UsersViewModelFactory(
    private val usersEmail: String = "",
    private val database: ProfileDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(usersEmail,database , application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}