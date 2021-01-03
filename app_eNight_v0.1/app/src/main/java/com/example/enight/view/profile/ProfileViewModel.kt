package com.example.enight.view.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val database: ProfileDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val currentProfile =  MutableLiveData<Profile>()

    val profiles : LiveData<List<Profile>>

    val allMail = ArrayList<String>()

    init {
        profiles = database.getAll()
        initCurrentProfile()
        //profiles = LiveData<List<Profile>>
    }

    private fun initializeListMail(){
        viewModelScope.launch {
            var index = 1L
            while(database.get(index) != null){
                val mail = database.get(index)
                allMail.add(mail!!.mail)
                index += 1
            }
        }
    }

    private fun initCurrentProfile(){
        viewModelScope.launch {
            currentProfile.value = database.getToProfile()
        }
    }


}