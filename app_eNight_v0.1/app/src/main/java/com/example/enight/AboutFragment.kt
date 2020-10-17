package com.example.enight

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enight.databinding.FragmentAboutMeBinding


class AboutFragment : Fragment() {

    private  val moi : MyName = MyName("48502","CLEREBAUT","Alistair")
    private lateinit var bindingAbout: FragmentAboutMeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        bindingAbout = DataBindingUtil.inflate<FragmentAboutMeBinding>(inflater,R.layout.fragment_about_me,
        container,false)
        bindingAbout.myName = moi

        return bindingAbout.root
    }
}