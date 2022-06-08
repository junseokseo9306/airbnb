package com.example.airbnb.ui.residents

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.common.CustomViewClick
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentResidentCountsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ResidentsCountsFragment : Fragment(), CustomViewClick {

    private lateinit var binding: FragmentResidentCountsBinding
    private var totalAdultCount = 0
    private var totalBabyCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_resident_counts, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incomingBundle = arguments?.getSerializable("reservationDetail") as SearchFilter
        Log.d("ResidentCountsFragment", incomingBundle.priceRange.toString())

        binding.customBar.setNextFragmentButtonListener(this)
        binding.customBar.setBackButtonListener(this)

        with(binding) {
            cvAdult.clickCounts.onEach {
                totalAdultCount = it
                updateHeaderFirstCountText(totalAdultCount)
            }.launchIn(CoroutineScope(Dispatchers.Main))

            cvKids.clickCounts.onEach {
                totalAdultCount = it
                updateHeaderFirstCountText(totalAdultCount)
            }.launchIn(CoroutineScope(Dispatchers.Main))

            cvBaby.clickCounts.onEach {
                totalBabyCount = it
                updateHeaderSecondCountText(totalBabyCount)
            }.launchIn(CoroutineScope(Dispatchers.Main))
        }

    }

    override fun goBackBefore() {
        findNavController().navigate(R.id.action_residentsCountsFragment_to_priceBarFragment)
    }

    override fun goNextFragment() {
        findNavController().navigate(R.id.action_residentsCountsFragment_to_googleMap)
    }

    private fun updateHeaderFirstCountText(count: Int) {
        val totalResidentCountStringBuilder = StringBuilder("")
        totalResidentCountStringBuilder.append("게스트 ")
        totalResidentCountStringBuilder.append(count)
        binding.customBar.setPriceMinRange(totalResidentCountStringBuilder.toString())
        totalResidentCountStringBuilder.clear()
    }

    private fun updateHeaderSecondCountText(count: Int) {
        val totalResidentCountStringBuilder = StringBuilder("")
        totalResidentCountStringBuilder.append(", 유아")
        totalResidentCountStringBuilder.append(count)
        binding.customBar.setPriceMaxRange(totalResidentCountStringBuilder.toString())
        totalResidentCountStringBuilder.clear()
    }

}