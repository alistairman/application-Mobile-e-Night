package com.example.enight.view.cour

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentCourBinding

/**
 * this class is the course fragment to represent diff course of this school year
 */
class CourFragment : Fragment() {

    private lateinit var drawer : DrawerLayout


    /**
     * this method create and set the fragment
     * set the view model, set the adapter
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * this part create the data binding of the fragment
         */
        val binding = DataBindingUtil.inflate<FragmentCourBinding>(
            inflater,R.layout.fragment_cour,
            container,false)

        drawer = binding.drawerLayout

        //val navController = this.findNavController(R.id.courFragment)
        NavigationUI.setupActionBarWithNavController(requireActivity() as AppCompatActivity,findNavController())
        NavigationUI.setupWithNavController(binding.navView, findNavController())

        /**
         * this part create the factory view model
         * create view model by using factory with arguments
         */
        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).courDatabaseDao
        val viewModelFactory = CourViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CourViewModel::class.java)

        /**
         * this part set view model with data binding
         */
        binding.courViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /**
         * this part create adapter for the recycle view
         */
        val adapter = CourAdapter(CourListener { courId ->
            Toast.makeText(context, courId , Toast.LENGTH_LONG).show()
            viewModel.onCourClicked(courId)
        })
        binding.recyclerViewCour.adapter = adapter

        /**
         * this part set the recycle view with data from view model
         */
        viewModel.coursesList.observe(viewLifecycleOwner, {
            it?.let { adapter.submitList(it) }
        })

        /**
         * this part check if the go to shop button is clicked then navigate to another fragment
         */
        viewModel.isGoToShop.observe(viewLifecycleOwner,{
            if(it) {
                this.findNavController().navigate(
                    CourFragmentDirections.actionCourFragmentToFoodTrucksFragment()
                )
                viewModel.done()
            }
        })

        //  food truck
        // =================================
        // cour detail


        viewModel.goToCourDetail.observe(viewLifecycleOwner, Observer { courId ->
            courId?.let {
                findNavController().navigate(CourFragmentDirections.actionCourFragmentToCourDetailFragment(courId))
                viewModel.onCourNavigated()
            }
        })

        /**
         * this part create a new manager of the recycle view with specific design
         */
        val manager = GridLayoutManager(activity,3)
        binding.recyclerViewCour.layoutManager = manager

        setHasOptionsMenu(true)
        return binding.root
    }

    /**override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp()
    }*/

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
