package com.example.airbnb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.adapters.SearchListAdapter
import com.example.airbnb.common.repeatOnLifecycleExtension
import com.example.airbnb.common.timestampToDateString
import com.example.airbnb.data.Accommodation
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentSearchResultBinding
import com.example.airbnb.viewmodels.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding

    private val viewModel: SearchResultViewModel by viewModels()

    private val adapter = SearchListAdapter()

    private var _accommodationList: List<Accommodation> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incomingBundle = arguments?.getSerializable("reservationDetail") as SearchFilter
        binding.toolbar.title = getString(
            R.string.search_result_string,
            incomingBundle.location,
            timestampToDateString(incomingBundle.checkInOut?.first!!, "yyyy년 MM월 dd일"),
            timestampToDateString(incomingBundle.checkInOut?.second!!, "yyyy년 MM월 dd일")
        )

        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(requireContext(), "Navigation Button", Toast.LENGTH_LONG).show()
        }
        binding.btnClose.setOnClickListener {
            Toast.makeText(requireContext(), "Close Button", Toast.LENGTH_LONG).show()
        }

        binding.accommodationList.adapter = adapter

        binding.btGotoMap.setOnClickListener {
            gotoMapFragment()
        }

        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.accommodationList.collect {
                binding.textRoomCount.text = getString(R.string.room_count, it.size)
                _accommodationList = it
                adapter.submitList(it)
            }
        }

        viewModel.getAccommodations(
            SearchFilter("양재", null, null, null)
        )
    }

    private fun gotoMapFragment() {
        val bundle = bundleOf("accommodationList" to _accommodationList)
        findNavController().navigate(R.id.action_searchResultFragment_to_googleMap, bundle)
    }
}