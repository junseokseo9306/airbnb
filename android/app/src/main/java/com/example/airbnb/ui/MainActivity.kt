package com.example.airbnb.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airbnb.R
import com.example.airbnb.databinding.ActivityMainBinding
import com.example.airbnb.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

private const val PERMISSION_REQUEST_CODE = 100

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        getMyLocation()

        homeViewModel.loadHomeContents()
        setupNav()
    }

    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(locationListener)
    }

    private fun setupNav() {
        val navController = supportFragmentManager.findFragmentById(R.id.navigation_fragment_main)
            ?.findNavController()
        navController?.let { controller ->
            binding.bottomNavigation.setupWithNavController(controller)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSION_REQUEST_CODE
            )
            return
        }
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            10000L,
            10.0F,
            locationListener
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Snackbar.make(binding.root, "Permission Granted", Snackbar.LENGTH_LONG).show()
                } else {
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        requestPermissions(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ), PERMISSION_REQUEST_CODE
                        )
                    } else {
                        showDialogToGetPermission()
                    }
                }
            }
        }
    }

    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permisisons request")
            .setMessage(
                "We need the location permission for some reason. You need to move on Settings to grant some permissions"
            )

        builder.setPositiveButton("OK") { _, _ ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        val dialog = builder.create()
        dialog.show()
    }

}