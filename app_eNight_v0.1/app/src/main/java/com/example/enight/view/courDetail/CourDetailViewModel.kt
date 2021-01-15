package com.example.enight.view.courDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import kotlinx.coroutines.launch

class CourDetailViewModel(
    private val courId:String,
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    private val cour = MutableLiveData<Cour>()
    fun getCour() = cour

    var nbCredit= MutableLiveData<String>()
    var valided= MutableLiveData<String>()

    init {
        initValues()
    }

    fun reInitValues(){
        initValues()
    }
    private fun initValues(){
        viewModelScope.launch {
            cour.value = database.getCour(courId)
            nbCredit.value =cour.value?.nbCredit.toString()+" "+"ECTS"
            val ok = cour.value?.valided
            if(ok!!){
                valided.value = "OK"
                _alreadyValided.value = true
            }
            else{
                valided.value = "NOK"
            }
        }

    }

    private val _alreadyValided = MutableLiveData<Boolean>()
    val alreadyValided: LiveData<Boolean>
        get() = _alreadyValided

    fun reinitValided(){
        _alreadyValided.value = false
    }
    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    private val _onCourValided = MutableLiveData<Boolean>()
    val onCourValided: LiveData<Boolean>
        get() = _onCourValided


    fun courValided(){
        viewModelScope.launch {
            val cour = database.getCour(courId)
            if(cour?.valided==false) cour.valided = true
            update(cour!!)
            _onCourValided.value = true
        }
    }
    fun doneValided(){
        _onCourValided.value = false
    }

    fun update(cour: Cour){
        viewModelScope.launch {
            database.update(cour)
        }
    }
}