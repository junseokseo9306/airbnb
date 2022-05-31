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

    companion object {
        private const val AIRBNB_SAMPLE_IMAGE = "https://news.airbnb.com/wp-content/uploads/sites/4/2019/06/PJM020719Q203_Luxe_ProvenceFR_Bedroom_1652_CandlesOut_R1.jpg?fit=2662,1776"
        private const val AIRBNB_SAMPLE_IMAGE2 = "https://news.airbnb.com/wp-content/uploads/sites/4/2022/04/065.jpg?w=1000"
        private const val AIRBNB_SAMPLE_IMAGE3 = "https://news.airbnb.com/wp-content/uploads/sites/4/2022/04/051.jpg?w=1000"
    }

}