package com.example.enight.view.foodTrucks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FoodTrucksFragmentBinding

/**
 * this fragment represent some food truck in town of Brussels
 */
class FoodTruckFragment : Fragment() {


    /**
     * this method create and set this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * this part create a view model by using factory with data base as argument
         */
        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).foodTruckDatabaseDao
        val viewModelFactory = FoodTruckViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(FoodTruckViewModel::class.java)

        /**
         * this part create and set the data binding and the viewmodel with binding part of view
         */
        val binding = FoodTrucksFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /**
         * this part create and set the adapter of recycle view
         */
        val adapterRecycleView = FoodTrucksAdapter(FoodTruckListener { foodTruckLocation ->
            Toast.makeText(context, foodTruckLocation , Toast.LENGTH_LONG).show()
            viewModel.onFoodTruckClicked(foodTruckLocation)
        })

        binding.recyclerViewFoodtrucks.adapter = adapterRecycleView

        /**
         * this part set the recycle view with data from the view model
         */
        viewModel.foodTruckList.observe(viewLifecycleOwner,{
            it?.let { adapterRecycleView.submitList(it)}
        })

        viewModel.findFoodTruck.observe(viewLifecycleOwner, { location ->
            location?.let {
                viewModel.onMapNavigated()
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