package com.example.enight

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enight.databinding.FoodTruksFragmentBinding

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
        return binding.root
    }

}