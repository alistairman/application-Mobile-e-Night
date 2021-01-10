package com.example.enight.view.cour

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import kotlinx.coroutines.launch

class CourViewModel(
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val editCour = MutableLiveData<String>()

    val editNbCredit = MutableLiveData<String>()

    val allCours = ArrayList<String>()

    init {
        initializeListMail()
    }

    fun ajouter(){

    }

    private fun initializeListMail(){
        viewModelScope.launch {
            var index = 1L
            while(database.get(index) != null){
                val mail = database.get(index)
                allCours.add(mail!!.cour)
                index += 1
            }
        }
    }
}