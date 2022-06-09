package com.example.airbnb.ui.residents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentResidentCountsBinding
import com.example.airbnb.viewmodels.ResidentCountsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResidentsCountsFragment : Fragment() {

    private lateinit var binding: FragmentResidentCountsBinding

    private val viewModel: ResidentCountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_resident_counts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val incomingBundle = arguments?.getSerializable("reservationDetail") as SearchFilter
        Log.d("ResidentCountsFragment", incomingBundle.priceRange.toString())

        setCustomBarListener()
        setViewModel()
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.cvAdult.setOnChangeClickCountListener(viewModel)
        binding.cvKids.setOnChangeClickCountListener(viewModel)
        binding.cvBaby.setOnChangeClickCountListener(viewModel)
    }

    private fun setCustomBarListener() {
        binding.customBar.setNextFragmentButtonClickListener {
            goNextFragment()
        }
        binding.customBar.setBackClickListener {
            goBackBefore()
        }
    }

    private fun goBackBefore() {
        findNavController().navigate(R.id.action_residentsCountsFragment_to_priceBarFragment)
    }

    private fun goNextFragment() {
        findNavController().navigate(R.id.action_residentsCountsFragment_to_searchResultFragment)
    }
}