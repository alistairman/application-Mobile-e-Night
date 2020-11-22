package com.example.enight.view.about

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * this class represent the view data of the about fragment
 */
class AboutViewModel : ViewModel(){

    /**
     * this class represent data about the programmer of this application
     */
    data class MyName(var matricule : String , var nom : String , var prenom : String )

    /**
     * this variable represent a mutable live data of data of programmer
     */
    private val _aboutMe = MutableLiveData<MyName>()
    val aboutMe: LiveData<MyName>
        get() = _aboutMe

    /**
     * this method initialize the data of this view
     */
    init {
        Log.i("AboutFragment", "view Model created")
        _aboutMe.value = MyName("48502","CLEREBAUT","Alistair")
    }
}