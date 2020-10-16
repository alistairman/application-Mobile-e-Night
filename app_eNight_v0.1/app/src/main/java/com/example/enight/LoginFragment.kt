package com.example.enight

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.enight.databinding.FragmentLoginBinding
//import androidx.fragment.app.Fragment


class LoginFragment : Fragment() {
    private lateinit var bindingLogin: FragmentLoginBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingLogin = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login,
            container, false
        )
        bindingLogin.apply {
            buttonConnexion.setOnClickListener {
                check(it)
            }

        }
        setHasOptionsMenu(true)
        return bindingLogin.root
    }

    private fun check(it: View) {
        val mail = bindingLogin.editEmail.text.trim()
        if (mail.matches(emailPattern.toRegex())) {
            Toast.makeText(requireActivity(), "Email Valided", Toast.LENGTH_LONG).show()
        } else {
            Snackbar.make(it, "Email not Valided", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }
}
