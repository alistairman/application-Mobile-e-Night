package com.example.enight.view.cour

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import kotlinx.coroutines.launch

/**
 * this class is the view model of the course fragment
 */
class CourViewModel(
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    /**
     * this is the edit text of the name of the course in the view
     */
    val editCourName = MutableLiveData<String>()

    /**
     * this is the edit text of number of ECTS of the course in the view
     */
    val editNbCredit = MutableLiveData<String>()

    /**
     * this is the list of courses from database used to set the recycle view
     */
    val coursesList = database.getAll()

    /**
     * this variable check if the go to shop button is clicked
     */
    private val _isGoToShop = MutableLiveData<Boolean>()
    val isGoToShop : LiveData<Boolean>
        get() = _isGoToShop

    private val _goToCourDetail = MutableLiveData<String>()
    val goToCourDetail
        get() = _goToCourDetail


    init {
        editCourName.value = ""
        editNbCredit.value = ""
    }

    /**
     * this method add a new course into the database
     * get value from course name edit text and number of ECTS edit text
     */
    fun addCourse(){
        var name = ""
        var nbCredit = ""

        if(!editCourName.value.isNullOrEmpty()){
            name = editCourName.value.toString()
        }
        if(!editNbCredit.value.isNullOrEmpty()){
            nbCredit = editNbCredit.value!!
        }
        if (!editCourName.value.isNullOrEmpty() && !editNbCredit.value.isNullOrEmpty()){
            val cour = Cour(name,nbCredit.toLong())
            insert(cour)
        }
    }

    /**
     * this method change the value of the variable isGoToShop if the button is clicked
     */
    fun isGoToShopClicked(){
        _isGoToShop.value = true
    }

    /**
     * this method reset the value if after navigate to the FoodTruck fragment
     */
    fun done(){
        _isGoToShop.value = false
    }

    /**
     * this method insert the new course into the database
     */
    fun insert(cour: Cour){
        viewModelScope.launch {
            database.insert(cour)
        }
    }

    /**
     * this method clear the courses table at the end of the school year
     */
    fun clear(){
        viewModelScope.launch {
            database.clear()
        }
    }

    fun onCourClicked(courId: String) {
        _goToCourDetail.value = courId
    }

    fun onCourNavigated() {
        _goToCourDetail.value = null
    }
}