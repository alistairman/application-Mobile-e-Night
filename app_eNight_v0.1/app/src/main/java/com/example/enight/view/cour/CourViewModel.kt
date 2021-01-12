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

    val editCourName = MutableLiveData<String>()

    val editNbCredit = MutableLiveData<String>()

    val allCour = ArrayList<String>()

    val cours = database.getAll()

    private val _isGoToShop = MutableLiveData<Boolean>()
    val isGoToShop : LiveData<Boolean>
        get() = _isGoToShop

    init {
        initializeListMail()
        editCourName.value = ""
        editNbCredit.value = ""
    }

    fun ajouter(){
        var name:String = ""
        var nbCredit:String = ""

        if(!editCourName.value.isNullOrEmpty()){
            name = editCourName.value.toString()
        }
        if(!editNbCredit.value.isNullOrEmpty()){
            nbCredit = editNbCredit.value!!
        }
        if (!editCourName.value.isNullOrEmpty() && !editNbCredit.value.isNullOrEmpty()){
            val cour = Cour(0,name,nbCredit.toLong())
            insert(cour)
        }
    }

    fun isCliked(){
        _isGoToShop.value = true
    }

    fun done(){
        _isGoToShop.value = false
    }

    private fun initializeListMail(){
        viewModelScope.launch {
            var index = 1L
            while(database.get(index) != null){
                val mail = database.get(index)
                allCour.add(mail!!.cour)
                index += 1
            }
        }
    }

    fun insert(cour: Cour){
        viewModelScope.launch {
            database.insert(cour)
        }
    }
}