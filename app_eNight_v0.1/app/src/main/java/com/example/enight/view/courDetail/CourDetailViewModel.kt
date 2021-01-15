package com.example.enight.view.courDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao

class CourDetailViewModel(
    private val courId:String = "ALG3",
    private val database: CourDatabaseDao,
    application: Application
) : ViewModel() {

    //private val cour: LiveData<Cour>

    //fun getCour() = cour

    var value :String =""

    init {
        value = courId
        //cour = database.getCourWithId(courId)
    }

    private val _onCourValided = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val onCourValided: LiveData<Boolean?>
        get() = _onCourValided

    private val _navigateToCour = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val navigateToCour: LiveData<Boolean?>
        get() = _navigateToCour


    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */
    fun doneNavigate() {
        _navigateToCour.value = null
    }

    fun onCloseClicked() {
        _navigateToCour.value = true
    }

    /**fun courValided(){
        cour.value?.valided = true
        _onCourValided.value = true
    }*/
}