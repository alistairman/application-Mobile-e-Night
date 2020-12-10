package com.example.enight.view.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.enight.R
import com.example.enight.dataBase.EmailDatabase
import com.example.enight.dataBase.EmailDatabaseDao
import com.example.enight.databinding.UsersBinding
import com.example.enight.view.login.LoginViewModel
import com.example.enight.view.login.LoginViewModelFactory

class UsersFragment : Fragment() {

    lateinit var binding : UsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.users, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = EmailDatabase.getInstance(application).emailDatabaseDao
        val viewModelFactory = UsersViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)
        binding.usersViewModel = viewModel

        val adapter = EmailAdapter()
        binding.usersList.adapter = adapter

        viewModel.emails.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }
}