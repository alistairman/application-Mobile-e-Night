package com.example.enight.view.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.users.ProfileDatabaseDao
import com.example.enight.dataBase.users.Profiles
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersEmail: String = "",
    private val database: ProfileDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    lateinit var profile :Profiles

    private val _userLogged = MutableLiveData<Boolean?>()
    val userLogged: LiveData<Boolean?>
        get() = _userLogged

    fun setUserProfile(){
        viewModelScope.launch {
            profile = database.getProfile(usersEmail) ?: return@launch
        }
    }
}