package com.example.enight.view.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.EmailDatabaseDao

/**
 * this class represent the factory of the fragment
 */
class LoginViewModelFactory(
    private val database: EmailDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(database , application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}