package com.example.enight.view.cour

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.enight.dataBase.cour.CourDatabaseDao

class CourViewModel(
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val editCour = MutableLiveData<String>()

    fun ajouter(){

    }
}