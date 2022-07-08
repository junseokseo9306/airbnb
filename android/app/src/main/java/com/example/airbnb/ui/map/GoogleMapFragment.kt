package com.example.airbnb.ui.map

import android.graphics.*
import android.os.Bundle
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentGoogleMapBinding
    private lateinit var mapView: MapView
    private lateinit var adapter: SearchListAdapter
    private lateinit var accommodationsList: List<Accommodation>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        p0.moveCamera(CameraUpdateFactory.zoomTo(16f))
        accommodationsList.forEach { accommodation ->
            val bitmap = drawCustomMarker(accommodation.price)
            val location = LatLng(accommodation.latitude, accommodation.longitude)
            val marker = MarkerOptions()
                .position(location)
                .title(accommodation.name)
                .snippet(accommodation.name)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .anchor(0.5f, 1f)
            p0.addMarker(marker)
        }
    }

    private fun drawCustomMarker(price: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(INITIAL_BITMAP_WIDTH,
            INITIAL_BITMAP_HEIGHT,
            Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val textColor = Paint().apply {
            color = Color.BLACK
            textSize = INITIAL_TEXT_SIZE
            style = Paint.Style.FILL
        }
        val rectColor = Paint().apply {
            color = context!!.getColor(R.color.off_white)
            style = Paint.Style.FILL_AND_STROKE
            strokeCap = Paint.Cap.ROUND
        }
        val decimalFormat = DecimalFormat("#,###")
        canvas.drawRoundRect(RectF(
            INITIAL_LEFT,
            INITIAL_TOP,
            INITIAL_WIDTH,
            INITIAL_HEIGHT), INITIAL_ROUND, INITIAL_ROUND, rectColor)
        canvas.drawText(
            getString(
                R.string.google_map_custom_text_price_marker,
                decimalFormat.format(price)
            ),
            INITIAL_TEXT_WIDTH_START,
            INITIAL_TEXT_HEIGHT_START,
            textColor
        )
        return bitmap
    }

    private fun viewPagerMove() {
        binding.vpAccommodationList.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val selectedPage = adapter.currentList[position]
                mapView.getMapAsync { googleMap ->
                    val cameraUpdate = CameraUpdateFactory.newLatLng(
                        LatLng(
                            selectedPage.latitude,
                            selectedPage.longitude
                        )
                    )
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

    companion object {
        const val INITIAL_BITMAP_HEIGHT = 80
        const val INITIAL_BITMAP_WIDTH = 240
        const val INITIAL_HEIGHT = 80F
        const val INITIAL_WIDTH = 240F
        const val INITIAL_TEXT_SIZE = 50f
        const val INITIAL_TEXT_HEIGHT_START = 60f
        const val INITIAL_TEXT_WIDTH_START = 12f
        const val INITIAL_LEFT = 0f
        const val INITIAL_TOP = 0f
        const val INITIAL_ROUND = 10f
    }
}