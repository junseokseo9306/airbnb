package com.example.airbnb.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.airbnb.R
import com.example.airbnb.adapters.SearchListAdapter
import com.example.airbnb.data.Accommodation
import com.example.airbnb.databinding.FragmentGoogleMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentGoogleMapBinding
    private lateinit var mapView: MapView
    private lateinit var adapter: SearchListAdapter
    private lateinit var accommodationsList: List<Accommodation>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_google_map, container, false)
        mapView = binding.googleMap
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val incomingData = arguments?.getSerializable("accommodationList") as List<Accommodation>
        accommodationsList = incomingData
        adapter = SearchListAdapter(false).apply {
            submitList(incomingData)
        }
        binding.vpAccommodationList.adapter = adapter
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        viewPagerMove()
        markerClick()
    }

    override fun onMapReady(p0: GoogleMap) {
        val firstLocation = LatLng(accommodationsList[0].latitude, accommodationsList[0].longitude)
        p0.moveCamera(CameraUpdateFactory.newLatLng(firstLocation))
        p0.moveCamera(CameraUpdateFactory.zoomTo(15f))
        accommodationsList.forEach { accommodation ->
            val location = LatLng(accommodation.latitude, accommodation.longitude)
            val marker = MarkerOptions()
                .position(location)
                .title(accommodation.name)
                .snippet(accommodation.name)
            p0.addMarker(marker)
        }
    }

    private fun viewPagerMove() {
        binding.vpAccommodationList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val selectedPage = adapter.currentList[position]
                mapView.getMapAsync { googleMap ->
                    val cameraUpdate = CameraUpdateFactory.newLatLng(LatLng(selectedPage.latitude, selectedPage.longitude))
                    googleMap.animateCamera(cameraUpdate)
                }
            }
        })
    }

    private fun markerClick() {
        mapView.getMapAsync { googleMap ->
            googleMap.setOnMarkerClickListener { marker ->
                val index = adapter.currentList.firstOrNull() { accommodation ->
                    accommodation.latitude == marker.position.latitude
                }
                val position = adapter.currentList.indexOf(index)
                binding.vpAccommodationList.currentItem = position
                true
            }
        }
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

}