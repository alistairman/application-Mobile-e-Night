package com.example.enight.view.foodTrucks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        })
        binding.recyclerViewFoodtrucks.adapter = adapterRecycleView

        /**
         * this part set the recycle view with data from the view model
         */
        viewModel.foodTruckList.observe(viewLifecycleOwner,{
            it?.let { adapterRecycleView.submitList(it)}
        })
        return binding.root
    }
}