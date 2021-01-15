package com.example.enight.view.courDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.CourDetailFragmentBinding
import com.example.enight.view.cour.CourViewModel
import com.example.enight.view.cour.CourViewModelFactory

class CourDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding= DataBindingUtil.inflate<CourDetailFragmentBinding>(
          inflater, R.layout.cour_detail_fragment,container, false)

        val application = requireNotNull(this.activity).application
        val arguments = CourDetailFragmentArgs.fromBundle(requireArguments())
        val dataSource = EnightDB.getInstance(application).courDatabaseDao
        val viewModelFactory = CourDetailViewModelFactory(arguments.courId,dataSource,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CourDetailViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToCour.observe(viewLifecycleOwner, Observer {
            if(it==true) {
                //this.findNavController().navigate(R.id.action_courFragment_to_courDetailFragment)
                //viewModel.doneNavigate()
            }
        })

        viewModel.onCourValided.observe(viewLifecycleOwner, Observer {
            if(it==true) binding.idButtonSetValided.visibility = View.GONE
        })

        return binding.root
    }
}