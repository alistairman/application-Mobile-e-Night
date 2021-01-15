package com.example.enight.view.courDetail

import android.app.Application
import androidx.lifecycle.*
import com.example.enight.dataBase.cour.Cour
import com.example.enight.dataBase.cour.CourDatabaseDao
import kotlinx.coroutines.launch

class CourDetailViewModel(
    private val courId:String = "ALG3",
    private val database: CourDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val cour: LiveData<Cour>
    fun getCour() = cour

    var nbCredit= MutableLiveData<String>()
    var valided= MutableLiveData<String>()

    init {
        cour = database.getCourWithId(courId)
        nbCredit.value = cour.value?.nbCredit.toString()
        valided.value = cour.value?.valided.toString()
        //initValues()
    }

    private fun initValues(){
        viewModelScope.launch {
            val course = database.getCour(courId)
            nbCredit.value = course?.nbCredit.toString()
            if (course != null) {
                valided.value = if(course.valided) "OK"
                else "NOK"
            }
        }

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