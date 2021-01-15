package com.example.enight.view.courDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

        viewModel.onCourValided.observe(viewLifecycleOwner, {
            if (it){
                requireView().findViewById<Button>(R.id.id_button_set_valided).visibility = View.GONE
                //viewModel.reInitValues()
                viewModel.doneValided()
            }
        })

        viewModel.alreadyValided.observe(viewLifecycleOwner, {
            if(it){
                requireView().findViewById<Button>(R.id.id_button_set_valided).visibility = View.GONE
                viewModel.reinitValided()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}