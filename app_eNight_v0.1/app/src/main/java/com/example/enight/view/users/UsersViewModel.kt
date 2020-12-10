package com.example.enight.view.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.EmailDatabaseDao
import kotlinx.coroutines.launch

class UsersViewModel(
    private val database: EmailDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val emails = database.getAll()
}