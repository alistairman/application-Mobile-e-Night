package com.example.enight.view.courDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.CourDetailFragmentBinding

/**
 * this class is the course detail fragment
 */
class CourDetailFragment : Fragment() {

    /**
     * this method create and set the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        /**
         * this part create and set the data binding of this course fragment
         */
        val binding= DataBindingUtil.inflate<CourDetailFragmentBinding>(
          inflater, R.layout.cour_detail_fragment,container, false)

        /**
         * this part create a view model by using a factory
         * the factory receive a argument
         */
        val application = requireNotNull(this.activity).application
        val arguments = CourDetailFragmentArgs.fromBundle(requireArguments())
        val dataSource = EnightDB.getInstance(application).courDatabaseDao
        val viewModelFactory = CourDetailViewModelFactory(arguments.courId,dataSource,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CourDetailViewModel::class.java)

        /**
         * this part set the binding part with the view model
         */
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        /**
         * this part is an observer if a course is valided
         * then hide the valided button
         */
        viewModel.onCourValided.observe(viewLifecycleOwner, {
            if (it){
                requireView().findViewById<Button>(R.id.id_button_set_valided).visibility = View.GONE
                viewModel.doneValided()
            }
        })

        /**
         * this part is an observer if the course is already valided
         * then doesn't important to show the button valided
         */
        viewModel.alreadyValided.observe(viewLifecycleOwner, {
            if(it){
                requireView().findViewById<Button>(R.id.id_button_set_valided).visibility = View.GONE
                viewModel.donetValided()
            }
        })

        /**
         * this row set the menu bar on the view
         */
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