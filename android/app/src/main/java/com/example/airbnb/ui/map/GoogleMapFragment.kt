package com.example.airbnb.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.airbnb.R
import com.example.airbnb.adapters.SearchListAdapter
import com.example.airbnb.data.Accommodation
import com.example.airbnb.databinding.FragmentGoogleMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentGoogleMapBinding
    private lateinit var mapView: MapView
    private lateinit var adapter: SearchListAdapter

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

        adapter = SearchListAdapter(false).apply {
            submitList(incomingData)
        }

        binding.vpAccommodationList.adapter = adapter

        mapView.onCreate(savedInstanceState)
    }

    override fun onMapReady(p0: GoogleMap) {
        Log.d("GoogleMap", "Is on start")
        val seoul = LatLng(37.566, 126.978)
        p0.moveCamera(CameraUpdateFactory.newLatLng(seoul))
        p0.moveCamera(CameraUpdateFactory.zoomTo(10f))
        val marker = MarkerOptions()
            .position(seoul)
            .title("서울")
            .snippet("아름다운 도시")
        p0.addMarker(marker)
        Log.d("GoogleMap", "Is on")
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        mapView.getMapAsync(this)
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