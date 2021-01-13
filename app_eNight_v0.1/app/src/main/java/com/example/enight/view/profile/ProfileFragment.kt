package com.example.enight.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentProfileBinding


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
        //binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = this

        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.select_dialog_item,
            viewModel.allMail
        )

        val actv : AutoCompleteTextView = binding.idEditTextEmail
        actv.threshold = 1
        actv.setAdapter(adapter)

        val adapter2 = ProfileAdapter()
        binding.profileListId.adapter = adapter2


        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter2.submitList(it)
            }
        })

        viewModel.isValid.observe(viewLifecycleOwner, { ok ->
            if (!ok) notValided()
        })

        viewModel.isEditEmailFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"Email is Empty", Toast.LENGTH_LONG).show()
        })

        viewModel.isEditNameFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"Last Name is Empty", Toast.LENGTH_LONG).show()
        })

        viewModel.isEditFirstNameFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"First Name is Empty", Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    private fun notValided(){
        Toast.makeText(activity, "Email Not Registered yet ", Toast.LENGTH_LONG).show()
    }
}