@file:Suppress("Annotator")

package com.example.enight.view.login

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

/**
 *this class represent the user interface of the login fragment
 */
class LoginFragment : Fragment() {

    /**
     * this is the data binding of this fragment
     */
    private lateinit var binding : FragmentLoginBinding

    /**
     * this is the view model for MVVM  architecture of this fragment
     */
    private lateinit var viewModel: LoginViewModel

    /**
     * this method make and build data and initialize the view of this fragment
     * initialize the autocomplete but till now doesn't work with database
     * and build menu of this fragment
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container, false
        )

        /**
         * this part is to create viewmodel with specific arguments using viewmodel factory
         */
        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).emailDatabaseDao
        val dataSourceProfile = EnightDB.getInstance(application).profileDatabaseDao
        val viewModelFactory = LoginViewModelFactory(dataSource,dataSourceProfile, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /**
         *this part is to create an adapter used to auto complete the edit text of the login view
         */
        val adapterAuto = ArrayAdapter(
            requireActivity(),
            android.R.layout.select_dialog_item,
            viewModel.allMail)

        val actv : AutoCompleteTextView = binding.editTextLoginEmail
        actv.threshold = 1
        actv.setAdapter(adapterAuto)

        /**
         * this part observe if the edit text of login view is correctly filled then make another action
         */
        viewModel.isEmailValid.observe(viewLifecycleOwner, { ok ->
            if (ok) emailValided()
            else emailNotValided()
        })

        /**
         * this is to set the menu bar
         */
        setHasOptionsMenu(true)
        return binding.root
    }


    /**
     * this method get the email string value from edittext and check if is valid or not
     * if is valided show the toast the notify is valid and ckeck datamail from database
     * if not show snackbar that the input mail is not valid
     */
    private fun emailValided(){
        Toast.makeText(activity, " Email Validé ", Toast.LENGTH_LONG).show()
        binding.editTextLoginEmail.setTextColor(BLACK)
        viewModel.getMail()
        //showCurrentMail()
        findNavController().navigate(R.id.action_loginFragment2_to_courFragment)
    }

    /**
     * this method show the snackbar if the email is not correctly filled
     * and change the color of input mail to red
     */
    private fun emailNotValided() {
        Snackbar.make(requireView(), "Email not Valided", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        binding.editTextLoginEmail.setTextColor(RED)
    }

    /**
     * this method show the connected mail with updated time and date
     */
    private fun showCurrentMail(){
        val log = viewModel.currentLog.value?.mailId.toString()+" - "+
                viewModel.currentLog.value?.mail+ " - "+
                viewModel.currentLog.value?.date+ " - "+
                viewModel.currentLog.value?.time
        Toast.makeText(requireActivity(), log, Toast.LENGTH_LONG).show()
    }


    /**
     * this method create the menu button in this fragment
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    /**
     * this method navigate to the correct fragment when item of menu is selected
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(
            item
        )
    }
}
