package com.example.enight.view.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao
import kotlinx.coroutines.launch

/**
 * this class is the view model part of the profile fragment
 */
class ProfileViewModel(
    private val database: ProfileDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    /**
     * this is the current profile of the current user
     */
    val currentProfile =  MutableLiveData<Profile>()

    /**
     * this is a list of all profile used to fill the recycle view
     */
    val profiles : LiveData<List<Profile>> = database.getAll()

    /**
     * this variable represent the edit text email of the view
     */
    val editEmail = MutableLiveData<String>()
    private val _isEditEmailFilled = MutableLiveData<Boolean>()
    val isEditEmailFilled : LiveData<Boolean>
        get() = _isEditEmailFilled

    /**
     * this variable represent the edit text last name of the view
     */
    val editName = MutableLiveData<String>()
    private val _isEditNameFilled = MutableLiveData<Boolean>()
    val isEditNameFilled : LiveData<Boolean>
        get() = _isEditNameFilled

    /**
     * this variable represent the edit text first name of the view
     */
    val editFirstName = MutableLiveData<String>()
    private val _isEditFirstNameFilled = MutableLiveData<Boolean>()
    val isEditFirstNameFilled : LiveData<Boolean>
        get() = _isEditFirstNameFilled

    /**
     * this is all email value from profile table to fill the auto complete of the edit email text
     */
    val allMail = ArrayList<String>()

    /**
     * this variable is used to notify the profile fragment is the email value filled is content
     * in the profile table
     *
     */
    private val _emailIsContent = MutableLiveData<Boolean>()
    val emailIsContent :LiveData<Boolean>
        get() = _emailIsContent

    /**
     * this method initialize the current profile,
     * fill the list of all email for the auto complete
     */
    init {
        initCurrentProfile()
        initializeListMail()
        editEmail.value = ""
        editFirstName.value = ""
        editName.value = ""
    }


    /**
     * this method initialize the current profile
     */
    private fun initCurrentProfile(){
        viewModelScope.launch {
            currentProfile.value = database.getToProfile()
        }
    }

    /**
     * this method fill the allMail variable with all email value in database
     */
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

    /**
     * this method update or add first name and last name of the user
     * and notify if one edit text is empty
     */
    fun addOrUpdateFirstLastName(){
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
                    _emailIsContent.value = false
                }
            }
        }else{
            _isEditEmailFilled.value = false
        }
    }

    /**
     * this method insert the new value of last name in database
     */
    private fun insertName(profile:Profile, name:String){
        viewModelScope.launch {
            profile.lastName = name
            database.update(profile)
        }
    }

    /**
     * this method insert the new value of the first name in database
     */
    private fun insertFirstName(profile:Profile, name:String){
        viewModelScope.launch {
            profile.firstName = name
            database.update(profile)
        }
    }
}