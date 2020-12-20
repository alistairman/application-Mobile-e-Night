package com.example.enight

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.enight.dataBase.Enight_Database
import com.example.enight.databinding.NightShopBinding

class NightShopFragment : Fragment() {

    lateinit var binding : NightShopBinding


    companion object {
        fun newInstance() = NightShopFragment()
    }

    private lateinit var viewModel: NightShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.night_shop, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = Enight_Database.getInstance(application).emailDatabaseDao
        //val viewModelFactory = NightShopViewModelFactory(dataSource, application)

        //val viewModel = ViewModelProvider(this, viewModelFactory).get(NightShopViewModel::class.java)
        //binding.night = viewModel

        //val adapter = EmailAdapter()
        //binding.usersList.adapter = adapter

        /**viewModel.emails.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })*/

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NightShopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}