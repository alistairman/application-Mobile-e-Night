package com.example.enight.view.cour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enight.R
import com.example.enight.dataBase.EnightDB
import com.example.enight.databinding.FragmentCourBinding

class CourFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCourBinding>(
            inflater,R.layout.fragment_cour,
            container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = EnightDB.getInstance(application).courDatabaseDao
        val viewModelFactory = CourViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CourViewModel::class.java)

        binding.courViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ArrayAdapter(
        requireActivity(),
        android.R.layout.select_dialog_item,
        viewModel.allCour
        )

        val actv : AutoCompleteTextView = binding.editInputCourName
        actv.threshold = 1
        actv.setAdapter(adapter)

        val adapter2 = CourAdapter()
        binding.recyclerViewCour.adapter = adapter2

        viewModel.cours.observe(viewLifecycleOwner, Observer {
            it?.let { adapter2.submitList(it) }
        })

        viewModel.isGoToShop.observe(viewLifecycleOwner,{
            if(it) {
                findNavController().navigate(R.id.action_courFragment_to_foodTruksFragment)
                viewModel.done()
            }
        })

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
app:courNbCreditTextview="@{cour}"
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
app:courValidedTextview="@{cour}"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintHorizontal_bias="0.5"
app:layout_constraintStart_toEndOf="@+id/nbCredit"
app:layout_constraintTop_toTopOf="parent"
tools:text="Excellent!!!" />*/