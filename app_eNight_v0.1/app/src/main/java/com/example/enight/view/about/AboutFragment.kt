@file:Suppress("Annotator", "Annotator", "Annotator", "Annotator", "Annotator")

package com.example.enight.view.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.enight.R
import com.example.enight.databinding.FragmentAboutMeBinding
import androidx.lifecycle.ViewModelProvider

/**
 * this class represent user interface of the about fragment
 */
class AboutFragment : Fragment() {

    /**
     * this variable is the reference to the view of this fragment
     */
    private lateinit var viewModel: AboutViewModel

    /**
     * this method initialize data and view when this fragment is created
     */
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        /**
         * this variable represent the data binding of this fragment
         */
        val bindingAbout: FragmentAboutMeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about_me,
            container,false)

        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)

        bindingAbout.aboutViewModel = viewModel

        bindingAbout.lifecycleOwner = viewLifecycleOwner

        return bindingAbout.root
    }
}