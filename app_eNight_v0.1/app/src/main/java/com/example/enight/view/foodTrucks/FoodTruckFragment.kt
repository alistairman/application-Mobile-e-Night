package com.example.enight.view.foodTrucks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enight.databinding.FoodTrucksFragmentBinding

/**
 * this fragment represent some food truck in town of Brussels
 */
class FoodTruckFragment : Fragment() {


    /**
     * this is the view model of this fragment
     * but the view model is create at the first time it'll be used
     */
    private val viewModel: FoodTruckViewModel by lazy {
        ViewModelProvider(this).get(FoodTruckViewModel::class.java)
    }

    /**
     * this method create and set this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * this part create and set the data binding and the viewmodel with binding part of view
         */
        val binding = FoodTrucksFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /**
         * this part create and set the adapter of recycle view
         */
        val adapterRecycleView = FoodTrucksAdapter()
        binding.recyclerViewFoodtrucks.adapter = adapterRecycleView

        /**
         * this part set the recycle view with data from the view model
         */
        viewModel.foodTrucksList.observe(viewLifecycleOwner,{
            it?.let {
                adapterRecycleView.data = it
            }
        })
        return binding.root
    }
}