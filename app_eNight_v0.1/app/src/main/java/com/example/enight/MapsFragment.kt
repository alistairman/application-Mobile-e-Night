package com.example.enight

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {


    private val callback = OnMapReadyCallback { googleMap ->

        val argument = MapsFragmentArgs.fromBundle(requireArguments())
        val location = argument.name
        val lat = argument.geometry[0].toDouble()
        val log = argument.geometry[1].toDouble()
        val foodTruck = LatLng(log,lat)
        val zoomLevel = 15f

        googleMap.addMarker(MarkerOptions().position(foodTruck).title(location))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foodTruck,zoomLevel))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}