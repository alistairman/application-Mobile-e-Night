package com.example.enight.view.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.enight.R
import com.example.enight.view.users.UsersViewModelFactory
import com.example.enight.dataBase.Enight_Database
import com.example.enight.databinding.UsersBinding

class UsersFragment : Fragment() {

    lateinit var binding : UsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.users, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = Enight_Database.getInstance(application).profileDatabaseDao
        val viewModelFactory = UsersViewModelFactory("",dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)
        binding.usersViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}