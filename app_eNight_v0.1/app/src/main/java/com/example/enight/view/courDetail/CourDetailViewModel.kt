package com.example.enight.view.courDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import kotlinx.coroutines.launch

/**
 * this class is the view model of the detail of course fragment
 */
class CourDetailViewModel(
    private val courId:String,
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    /**
     * this variable is the course receive by argument
     */
    private val cour = MutableLiveData<Cour>()
    fun getCour() = cour

    /**
     * this variable is the number of ETCS of course
     */
    var nbCredit= MutableLiveData<String>()

    /**
     * this variable is the notification if the course is valided
     */
    var valided= MutableLiveData<String>()

    /**
     * this method initialize the course clicked from the course fragement
     */
    init {
        initValues()
    }

    /**
     * this method get the course from the database with the id given by argument
     * and set the values of the view with the data from course
     */
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

    /**
     * this variable check if the course if already valided
     */
    private val _alreadyValided = MutableLiveData<Boolean>()
    val alreadyValided: LiveData<Boolean>
        get() = _alreadyValided

    /**
     * this method reset the value of variable that observe the course valided
     */
    fun donetValided(){
        _alreadyValided.value = false
    }

    /**
     * this variable check if the course if valided
     * it's observed from the course detail fragment
     */
    private val _onCourValided = MutableLiveData<Boolean>()
    val onCourValided: LiveData<Boolean>
        get() = _onCourValided


    /**
     * this method up date the course valided into database and notify the course detail fragment
     * to change the course value
     */
    fun courValided(){
        viewModelScope.launch {
            val cour = database.getCour(courId)
            if(cour?.valided==false) cour.valided = true
            update(cour!!)
            _onCourValided.value = true
            valided.value = "OK"
        }
    }

    /**
     * this method reset the value of valided course
     */
    fun doneValided(){
        _onCourValided.value = false
    }

    /**
     * this method updata course into database
     */
    fun update(cour: Cour){
        viewModelScope.launch {
            database.update(cour)
        }
    }
}