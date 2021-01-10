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

    val profiles : LiveData<List<Profile>> = database.getAll()

    val editEmail = MutableLiveData<String>()
    private val _isEditEmailFilled = MutableLiveData<Boolean>()
    val isEditEmailFilled : LiveData<Boolean>
        get() = _isEditEmailFilled

    val editName = MutableLiveData<String>()
    private val _isEditNameFilled = MutableLiveData<Boolean>()
    val isEditNameFilled : LiveData<Boolean>
        get() = _isEditNameFilled

    val editFirstName = MutableLiveData<String>()
    private val _isEditFirstNameFilled = MutableLiveData<Boolean>()
    val isEditFirstNameFilled : LiveData<Boolean>
        get() = _isEditFirstNameFilled

    val allMail = ArrayList<String>()

    private val _isValid = MutableLiveData<Boolean>()
    val isValid :LiveData<Boolean>
        get() = _isValid

    init {
        initCurrentProfile()
        initializeListMail()
        editEmail.value = ""
        editFirstName.value = ""
        editName.value = ""
    }


    private fun initCurrentProfile(){
        viewModelScope.launch {
            currentProfile.value = database.getToProfile()
        }
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

    fun ajouter(){
        if(!editEmail.value.isNullOrEmpty()){
            viewModelScope.launch {
                val profileGot = database.getProfile(editEmail.value.toString())

                if(profileGot != null){
                    if(!editName.value.isNullOrEmpty()){
                        insertName(profileGot,editName.value.toString())
                    }else{
                        _isEditNameFilled.value = false
                    }
                    if(!editFirstName.value.isNullOrEmpty()){
                        insertFirstName(profileGot,editFirstName.value.toString())
                    }else{
                        _isEditFirstNameFilled.value = false
                    }
                }
                else{
                    _isValid.value = false
                }
            }
        }else{
            _isEditEmailFilled.value = false
        }
    }

    fun insertName(profile:Profile,name:String){
        viewModelScope.launch {
            profile.lastName = name
            database.update(profile)
        }
    }

    fun insertFirstName(profile:Profile,name:String){
        viewModelScope.launch {
            profile.firstName = name
            database.update(profile)
        }
    }




}