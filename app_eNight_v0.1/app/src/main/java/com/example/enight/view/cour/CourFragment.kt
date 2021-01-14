package com.example.enight.view.cour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentCourBinding

/**
 * this class is the course fragment to represent diff course of this school year
 */
class CourFragment : Fragment() {

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
        val adapter = CourAdapter()
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
                findNavController().navigate(R.id.action_courFragment_to_foodTrucksFragment)
                viewModel.done()
            }
        })

        /**
         * this part create a new manager of the recycle view with specific design
         */
        val manager = GridLayoutManager(activity,3)
        binding.recyclerViewCour.layoutManager = manager

        return binding.root
    }
}
/**
<TextView
android:id="@+id/nbCredit"
android:layout_width="101dp"
android:layout_height="18dp"
android:textAlignment="center"
app:courNbCreditTextView="@{cour}"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toStartOf="@+id/valided"
app:layout_constraintHorizontal_bias="0.5"
app:layout_constraintStart_toEndOf="@+id/cour_name"
app:layout_constraintTop_toTopOf="parent"
tools:text="Excellent!!!" />

<TextView
android:id="@+id/valided"
android:layout_width="101dp"
android:layout_height="18dp"
android:textAlignment="center"
app:courValidedTextView="@{cour}"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintHorizontal_bias="0.5"
app:layout_constraintStart_toEndOf="@+id/nbCredit"
app:layout_constraintTop_toTopOf="parent"
tools:text="Excellent!!!" />*/