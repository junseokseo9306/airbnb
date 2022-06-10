package com.example.airbnb.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.airbnb.BuildConfig
import com.example.airbnb.R
import com.example.airbnb.adapters.CityItemAdapter
import com.example.airbnb.common.*
import com.example.airbnb.data.Image
import com.example.airbnb.databinding.FragmentHomeBinding
import com.example.airbnb.di.NetworkModule
import com.example.airbnb.viewmodels.HomeViewModel
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private val adapter = CityItemAdapter {
        onItemClicked()
    }
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var activityContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageUrl = Image(NetworkModule.HERO_IMAGE_URL)

        setupViews()
        setupObserver()
        onTextClicked()
        requestPermissionResult()
    }

    private fun setupViews() {
        val gridLayoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.HORIZONTAL, false)
        with(binding) {
            rvCities.adapter = adapter
            rvCities.layoutManager = gridLayoutManager
            swipeRefreshLayout.setOnRefreshListener {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            composeView.setContent {
                AppCompatTheme {
                    val data = listOf(
                        Accommodation(AIRBNB_SAMPLE_IMAGE, "오늘 하루도 쉴 수 있는 숙소"),
                        Accommodation(AIRBNB_SAMPLE_IMAGE2, "자연경관이 멋진 우기네 집"),
                        Accommodation(AIRBNB_SAMPLE_IMAGE3, "경주에서 볼 수 있는 멋진 야경"),
                        Accommodation(AIRBNB_SAMPLE_IMAGE, "오늘 하루도 쉴 수 있는 숙소"),
                        Accommodation(AIRBNB_SAMPLE_IMAGE2, "자연경관이 멋진 우기네 집"),
                        Accommodation(AIRBNB_SAMPLE_IMAGE3, "경주에서 볼 수 있는 멋진 야경")
                    )
                    AccommodationList(data)
                }
            }
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.cityInfo.collect {
                adapter.submitList(it)
            }
        }
    }

    private fun onTextClicked() {
        with(binding) {
            tvWhereToTravel.setOnClickListener {
                moveToSearchFragment()
            }
            popUpCalendar()
        }
    }

    private fun popUpCalendar() {
        with(binding) {
            ibSearchButton.setOnClickListener {
                moveToSearchFragment()
//                findNavController().navigate(R.id.action_homeFragment_to_googleMap)
//                calendarPopUp.setUpDefaultCalendar()
//                val constraintsBuilder =
//                    CalendarConstraints.Builder()
//                        .setValidator(DateValidatorPointForward.now())
//
//                val dateRangePicker =
//                    MaterialDatePicker.Builder.dateRangePicker()
//                        .setTitleText("Select dates")
//                        .setCalendarConstraints(constraintsBuilder.build())
//                        .setTheme(R.style.CalendarTheme)
//                        .build()
//
//                dateRangePicker.addOnPositiveButtonClickListener {
//                    val backButtonFunctionPointer = HomeFragmentDirections.actionHomeFragmentToPriceBar()
//                    findNavController().navigate(R.id.action_homeFragment_to_priceBar)
//                }
//                dateRangePicker.show(childFragmentManager, "CALENDAR")
            }
        }
    }

    private fun onItemClicked() {
        moveToSearchFragment()
    }

    private fun moveToSearchFragment() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSearchFragment(adapter.currentList.toTypedArray())
        findNavController().navigate(action)
    }

    private fun requestPermissionResult() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissionsResult(permissions, object : OnRequestPermissionListener {
            override fun onGranted() {
                viewModel.getCityList()
            }

            override fun onDenied(deniedPermissions: List<String>) {
                binding.root.showSnackbar(
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE,
                    R.string.settings
                ) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        if (activityContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) ||
            activityContext.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            locationManager =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

            locationListener = LocationListener { location ->
                val latitude = location.latitude
                val longitude = location.longitude
                Log.d("HomeFragment", "latitude : $latitude, longitude : $longitude")
            }

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10000L,
                10.0F,
                locationListener
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (activityContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) ||
            activityContext.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                viewModel.setMyLocation(location.latitude, location.longitude)
            }
            Log.d("HomeFragment", location.toString())
        }
    }

    companion object {
        private const val AIRBNB_SAMPLE_IMAGE =
            "https://news.airbnb.com/wp-content/uploads/sites/4/2019/06/PJM020719Q203_Luxe_ProvenceFR_Bedroom_1652_CandlesOut_R1.jpg?fit=2662,1776"
        private const val AIRBNB_SAMPLE_IMAGE2 =
            "https://news.airbnb.com/wp-content/uploads/sites/4/2022/04/065.jpg?w=1000"
        private const val AIRBNB_SAMPLE_IMAGE3 =
            "https://news.airbnb.com/wp-content/uploads/sites/4/2022/04/051.jpg?w=1000"
    }

}