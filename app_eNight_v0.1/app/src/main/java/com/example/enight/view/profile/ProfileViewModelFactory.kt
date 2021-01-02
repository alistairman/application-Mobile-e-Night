package com.example.enight.view.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enight.dataBase.profile.ProfileDatabaseDao

class ProfileViewModelFactory(
    private val database : ProfileDatabaseDao,
    private val application: Application
    ): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(database, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}