@file:Suppress("Annotator")

package com.example.enight.view.login

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
import com.example.enight.dataBase.EmailDatabase
import com.example.enight.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

/**
 *this class represent the user interface of the login fragment
 */
class LoginFragment : Fragment() {

    /**
     * this variable represent the data binding of this fragment
     */
    private lateinit var bindingLogin: FragmentLoginBinding

    /**
     * this variable represent the view of this fragment
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
    ): View? {

        bindingLogin = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = EmailDatabase.getInstance(application).emailDatabaseDao
        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        bindingLogin.loginViewModel = viewModel
        bindingLogin.lifecycleOwner = viewLifecycleOwner

        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.select_dialog_item,
            viewModel.allMail
        )

        val actv : AutoCompleteTextView = bindingLogin.editEmail
        actv.threshold = 1
        actv.setAdapter(adapter)

        viewModel.isValid.observe(viewLifecycleOwner, { ok ->
            if (ok) valided()
            else notValided()

        })

        setHasOptionsMenu(true)
        return bindingLogin.root
    }


    /**
     * this method get the email string value from edittext and check if is valid or not
     * if is valided show the toast the notify is valid and ckeck datamail from database
     * if not show snackbar that the input mail is not valid
     */
    private fun valided(){
        Toast.makeText(activity, "Email Valided", Toast.LENGTH_LONG).show()
        viewModel.getMail()
        showCurrentMail()
    }

    private fun notValided() {
        Snackbar.make(requireView(), "Email not Valided", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        bindingLogin.editEmail.setTextColor(RED)
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
