package com.example.enight.view.cour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentCourBinding

class CourFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCourBinding>(
            inflater,R.layout.fragment_cour,
            container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).courDatabaseDao
        val viewModelFactory = CourViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CourViewModel::class.java)

        binding.courViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ArrayAdapter(
        requireActivity(),
        android.R.layout.select_dialog_item,
        viewModel.allCours
        )

        val actv : AutoCompleteTextView = binding.editInputCourName
        actv.threshold = 1
        actv.setAdapter(adapter)

        //val adapter2 = CourAdapter()
        //binding.recyclerViewCours.adapter = adapter2


        /**viewModel.cours.observe(viewLifecycleOwner, {
            it?.let { adapter2.data = it }
        })*/

        return binding.root
    }
}