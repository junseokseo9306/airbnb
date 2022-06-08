package com.example.airbnb.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.airbnb.R
import com.example.airbnb.databinding.FragmentSearchBinding
import com.example.airbnb.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding

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
    }
}