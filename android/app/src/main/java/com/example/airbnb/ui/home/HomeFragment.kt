package com.example.airbnb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.airbnb.R
import com.example.airbnb.data.Image
import com.example.airbnb.databinding.FragmentHomeBinding
import com.example.airbnb.di.NetworkModule
import com.example.airbnb.viewmodels.HomeViewModel
import com.google.accompanist.appcompattheme.AppCompatTheme
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private val adapter = HomeAdapter(this::onItemClicked)

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
        binding.composeView.setContent {
            AppCompatTheme {
                val data = listOf(
                    Accommodation("AA", "BB"),
                    Accommodation("AA", "CC"),
                    Accommodation("AA", "DD"),
                    Accommodation("AA", "EE"),
                    Accommodation("AA", "FF"),
                    Accommodation("AA", "GG")
                )

                AccommodationList(data)
            }
        }
    }

    private fun setupViews() {
        val gridLayoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.HORIZONTAL, false)
        binding.rvCities.adapter = adapter
        binding.rvCities.layoutManager = gridLayoutManager
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cityInfo.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun onTextClicked() {
        with(binding) {
            tvWhereToTravel.setOnClickListener {
                moveToSearchFragment()
            }
            ibSearchButton.setOnClickListener {
                moveToSearchFragment()
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
}