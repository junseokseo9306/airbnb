package com.example.airbnb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airbnb.R
import com.example.airbnb.viewmodels.HomeViewModel
import com.example.airbnb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        homeViewModel.loadHomeContents()
        setupNav()
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

}