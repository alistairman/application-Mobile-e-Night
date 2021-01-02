package com.example.enight.view.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val database: ProfileDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val currentProfile =  MutableLiveData<Profile>()

    init {
        initCurrentProfile()
    }

    private fun initCurrentProfile(){
        viewModelScope.launch {
            currentProfile.value = database.getToProfile()
        }
        currentProfile
    }


}