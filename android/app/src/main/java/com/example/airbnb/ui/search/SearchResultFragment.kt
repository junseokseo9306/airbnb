package com.example.airbnb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.adapters.SearchListAdapter
import com.example.airbnb.common.repeatOnLifecycleExtension
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentSearchResultBinding
import com.example.airbnb.viewmodels.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding

    private val viewModel: SearchResultViewModel by viewModels()

    private val adapter = SearchListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(requireContext(), "Navigation Button", Toast.LENGTH_LONG).show()
        }
        binding.btnClose.setOnClickListener {
            Toast.makeText(requireContext(), "Close Button", Toast.LENGTH_LONG).show()
        }

        binding.accommodationList.adapter = adapter

        viewLifecycleOwner.repeatOnLifecycleExtension {
            viewModel.accommodationList.collect {
                binding.textRoomCount.text = getString(R.string.room_count, it.size)
                adapter.submitList(it)
            }
        }

        viewModel.getAccommodations(
            SearchFilter("양재", null, null, null)
        )
    }
}