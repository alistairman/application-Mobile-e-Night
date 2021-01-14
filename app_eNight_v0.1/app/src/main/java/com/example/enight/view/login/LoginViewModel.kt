
package com.example.enight.view.login

import android.annotation.SuppressLint
import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.enight.dataBase.email.Email
import com.example.enight.dataBase.email.EmailDatabaseDao
import com.example.enight.dataBase.profile.Profile
import com.example.enight.dataBase.profile.ProfileDatabaseDao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

/**
 * this is the view model part of MVVM of user interface
 */
class LoginViewModel(
    private val database: EmailDatabaseDao,
    private val databaseProfile: ProfileDatabaseDao,
    application: Application ) : AndroidViewModel(application) {


    /**
     * this variable is the live data of the current mail logged
     */
    var currentLog = MutableLiveData<Email?>()


    /**
     * this variable is the array of the all mail from the database
     */
    val allMail = ArrayList<String>()

    /**
     * this variable get the value from the editText of Login
     */
    val emailText = MutableLiveData<String>()

    /**
     * this variable get the true if the email is valided
     */
    private val _isEmailValid = MutableLiveData<Boolean>()
         val isEmailValid :LiveData<Boolean>
        get() = _isEmailValid

    /**
     * this method initialize the current mail logged
     * and initialize the array of all mail from database
     */
    init {
        viewModelScope.launch {
            initializeCurrentLog()
            initializeListMail()
            emailText.value = ""
        }
    }


    /**
     * this method initialize the current mail logged
     */
    private suspend fun initializeCurrentLog() {
        currentLog.value = database.getToMail()
    }

    /**
     * this method initialize the array 'allMail' with all mail from database
     */
    private fun initializeListMail(){
        viewModelScope.launch {
            var index = 1L
            while(database.get(index) != null){
                val mail = database.get(index)
                allMail.add(mail!!.mail)
                index += 1
            }
        }
    }

    /**
     * this method check if input mail is not empty
     * and if the email is in valid format
     */
    fun onConnexion(){
        _isEmailValid.value = emailText.value!!.isNotEmpty()
                && Patterns.EMAIL_ADDRESS.matcher(emailText.value!!.trim()).matches()
    }

    /**
     * this method find and get mail from inputed from edited text in database
     */
    fun getMail(){
        viewModelScope.launch {
            val value = emailText.value.toString()
            val mail = database.getMail(value)
            if (mail?.mail != value) emailNotFound(value)
            else emailFound(mail)
        }
    }


    /**
     * this method is called when the logged mail is found in database
     * then updata the time and date of the mail when logged
     */
    @SuppressLint("SimpleDateFormat")
    private suspend fun emailFound(mail: Email) {
        val time = System.currentTimeMillis()
        mail.date = SimpleDateFormat("EEE, MMM dd , ''yy").format(time).toString()
        mail.time = SimpleDateFormat("HH:mm:ss").format(time).toString()
        viewModelScope.launch {
            update(mail)
        }
        viewModelScope.launch {
            currentLog.value = database.getMail(mail.mail)
        }
    }

    /**
     * this method is called when the mail logged is not found in database
     * then the new email is save into the database and the currentlog is updated
     */
    @SuppressLint("SimpleDateFormat")
    private suspend fun emailNotFound(value: String){
        val time = System.currentTimeMillis()
        val dateString = SimpleDateFormat("EEE, MMM dd , ''yy").format(time).toString()
        val timeString = SimpleDateFormat("HH:mm:ss").format(time).toString()
        val mail = Email(0,value,dateString,timeString)
        val profile = Profile(0,mail.mail)

        viewModelScope.launch {
            insert(mail)
        }
        viewModelScope.launch {
            insertProfile(profile)
        }
        viewModelScope.launch {
            initializeCurrentLog()
        }
    }

    /**
     * this method insert the new mail into the database
     */
    private suspend fun insert(mail: Email) {
        database.insert(mail)
    }

    /**
     * this method update the currentmail into the database
     */
    private suspend fun update(mail: Email) {
        database.update(mail)
    }

    /**
     * this method insert a new profile with the new email given into the profile table
     */
    private suspend fun insertProfile(mail: Profile) {
        databaseProfile.insert(mail)
    }


}