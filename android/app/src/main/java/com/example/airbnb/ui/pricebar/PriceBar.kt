package com.example.airbnb.ui.pricebar

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.airbnb.R
import com.example.airbnb.data.PriceRange
import com.example.airbnb.databinding.FragmentPricebarBinding
import com.stfalcon.pricerangebar.model.BarEntry
import java.lang.StringBuilder

class PriceBar() : Fragment() {

    private lateinit var binding: FragmentPricebarBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pricebar, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barEntrys = ArrayList<BarEntry>()

        barEntrys.add(BarEntry(1.0f, 50.0f))
        barEntrys.add(BarEntry(2.0f, 70.0f))
        barEntrys.add(BarEntry(3.0f, 100.0f))
        barEntrys.add(BarEntry(4.0f, 200.0f))
        barEntrys.add(BarEntry(5.0f, 50.0f))
        barEntrys.add(BarEntry(6.0f, 50.0f))
        barEntrys.add(BarEntry(7.0f, 50.0f))
        barEntrys.add(BarEntry(8.0f, 50.0f))
        barEntrys.add(BarEntry(9.0f, 50.0f))
        barEntrys.add(BarEntry(10.0f, 50.0f))
        barEntrys.add(BarEntry(11.0f, 10.0f))
        barEntrys.add(BarEntry(12.0f, 10.0f))
        barEntrys.add(BarEntry(13.0f, 10.0f))
        barEntrys.add(BarEntry(14.0f, 10.0f))
        barEntrys.add(BarEntry(15.0f, 10.0f))
        barEntrys.add(BarEntry(16.0f, 10.0f))
        barEntrys.add(BarEntry(17.0f, 10.0f))
        barEntrys.add(BarEntry(18.0f, 10.0f))

        var rightValue = 0
        var leftValue = 0
        val priceRangeStringBuilder = StringBuilder("")
        with(binding) {
            stfRangeBar.setEntries(barEntrys)
            stfRangeBar.onRightPinChanged = { rightPinIndex, rightPinValueString ->
                rightValue = rightPinIndex
                Log.d(
                    "PriceBar",
                    "index: $rightPinIndex, rightpin: $rightPinValueString value ${barEntrys[rightValue].y}"
                )
            }
            stfRangeBar.onLeftPinChanged = { leftPinIndex, leftPinValueString ->
                leftValue = leftPinIndex
                Log.d(
                    "PriceBar",
                    "index: $leftPinIndex, rightpin: $leftPinValueString value: ${barEntrys[leftValue].y}"
                )
            }
            priceRangeStringBuilder.append(leftValue)
            priceRangeStringBuilder.append(rightValue)
            priceRange = PriceRange(priceRangeStringBuilder.toString())
        }


    }

}