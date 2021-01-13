package com.example.enight.view.foodTrucks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.enight.cache.FoodTruckModel
import com.example.enight.databinding.FoodTruksFragmentBinding
import com.example.enight.view.profile.ProfileAdapter

class FoodTruksFragment : Fragment() {


    private val viewModel: FoodTruksViewModel by lazy {
        ViewModelProvider(this).get(FoodTruksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FoodTruksFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter2 = FoodTrucksAdapter()
        binding.recyclerViewFoodtrucks.adapter = adapter2

        /**viewModel.foodTrucksList.observe(viewLifecycleOwner,{
            it?.let {
                adapter2.data = it
            }
        })*/
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<FoodTruckModel>>{foodTruck ->
            foodTruck.apply {
                adapter2.data = foodTruck
            }
        })



        return binding.root
    }

}