package com.example.enight.view.profile

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.dataBase.profile.Profile
import com.example.enight.databinding.FragmentProfileBinding
import com.example.enight.view.login.LoginViewModelFactory


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentProfileBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).profileDatabaseDao
        val viewModelFactory = ProfileViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)

        binding.profileViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ProfileAdapter()
        binding.profileListId.adapter = adapter

        /**viewModel.currentProfile.observe(viewLifecycleOwner,{
            it?.let {
                val pr = ArrayList<Profile>()
                pr.add(it)
                adapter.data = pr }
        })*/

        viewModel.profiles.observe(viewLifecycleOwner, {
            it?.let { adapter.data = it }
        })

        return binding.root
    }
}