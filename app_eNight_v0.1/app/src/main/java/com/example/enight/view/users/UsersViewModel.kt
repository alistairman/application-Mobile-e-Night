package com.example.enight.view.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enight.dataBase.email.EmailDatabaseDao

class UsersViewModel(
    private val database: EmailDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val emails = database.getAll()
}