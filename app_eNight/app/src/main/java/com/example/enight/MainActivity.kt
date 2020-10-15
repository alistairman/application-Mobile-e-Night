package com.example.enight

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.enight.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.apply {
            buttonConnexion.setOnClickListener{ checkEmail()}
            buttonConnexion.visibility = View.GONE
            if(editEmail.text.toString()[0].isLetter()) buttonConnexion.visibility = View.VISIBLE
        }
    }

    private fun checkEmail(){
        val mail = binding.editEmail.text.toString().trim()
        if(mail.matches(emailPattern.toRegex())){
            Toast.makeText(this, " Email Valided", Toast.LENGTH_LONG).show()
        }
        else{
            // Snackbar.make(it, "This FAB needs an action!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            Toast.makeText(this, " Email not Valided", Toast.LENGTH_LONG).show()

        }
    }
}