package com.example.enight.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentProfileBinding

/**
 * this is the profile fragment of the a user
 * is used to see some info about the user
 */
class ProfileFragment : Fragment() {

    /**
     * this method create and config the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * this part create the data binding for this fragment
         */
        val binding : FragmentProfileBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )

        /**
         * this part config and create the view model of the fragement
         */
        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).profileDatabaseDao
        val viewModelFactory = ProfileViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)

        /**
         * this part set the data binding with the data from the view model
         */
        binding.profileViewModel = viewModel
        binding.lifecycleOwner = this

        /**
         * this part create an adapter to auto complete the edittext of profile
         */
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.select_dialog_item,
            viewModel.allMail)

        val actv : AutoCompleteTextView = binding.idEditTextEmail
        actv.threshold = 1
        actv.setAdapter(adapter)

        /**
         * this part set the recycle view with data binding from view model
         */
        val adapterRecycleView = ProfileAdapter()
        binding.profileListId.adapter = adapterRecycleView

        /**
         * this part observe data from view model to fill the recycle view
         */
        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterRecycleView.submitList(it)
            }
        })

        /**
         * this part check if the email filled is content in database
         * if not show a toast to notify the user
         */
        viewModel.emailIsContent.observe(viewLifecycleOwner, { ok ->
            if (!ok) notValided()
        })

        /**
         * this part check if the edit text of email is filled
         * if not show a toast to notify the user
         */
        viewModel.isEditEmailFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"Email is Empty", Toast.LENGTH_LONG).show()
        })

        /**
         * this part check if the edit text of last name if filled
         * if not show a toast to notify the user
         */
        viewModel.isEditNameFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"Last Name is Empty", Toast.LENGTH_LONG).show()
        })

        /**
         * this part check if the edit text of first name is filled
         * if not show a toast to notify the user
         */
        viewModel.isEditFirstNameFilled.observe(viewLifecycleOwner, {
            if(!it) Toast.makeText(activity,"First Name is Empty", Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    /**
     * this method is called to notify the user that the email is not registered in database
     */
    private fun notValided(){
        Toast.makeText(activity, "Email Not Registered yet ", Toast.LENGTH_LONG).show()
    }
}