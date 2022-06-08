package com.example.airbnb.ui.pricebar

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.common.CustomViewClick
import com.example.airbnb.data.CustomText
import com.example.airbnb.databinding.FragmentPricebarBinding
import com.example.airbnb.ui.calendar.CustomCalendar
import com.stfalcon.pricerangebar.model.BarEntry
import kotlin.text.StringBuilder

class PriceBarFragment() : Fragment(), CustomViewClick {

    private lateinit var binding: FragmentPricebarBinding
    private val calendarPopUp: CustomCalendar by lazy {
        CustomCalendar(this, null)
    }

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

        val roomPrice = mutableListOf<Int>()
        roomPrice.add(10000)
        roomPrice.add(10000)
        roomPrice.add(10000)
        roomPrice.add(40000)
        roomPrice.add(50000)
        roomPrice.add(50000)
        roomPrice.add(60000)
        roomPrice.add(60000)
        roomPrice.add(100000)
        roomPrice.add(10000)
        roomPrice.add(10000)
        roomPrice.add(10000)
        roomPrice.add(40000)

        val barEntrys = ArrayList<BarEntry>()

        var initialX = 0f
        roomPrice.forEach { price ->
            barEntrys.add(BarEntry(initialX, INITIAL_VALUE))
            barEntrys.add(BarEntry(initialX++, price.div(1000).toFloat()))
            barEntrys.add(BarEntry(initialX, price.div(1000).toFloat()))
            barEntrys.add(BarEntry(initialX++, INITIAL_VALUE))
        }


        val priceRangeStringBuilder = StringBuilder("")
        with(binding) {
            customBar.setBackButtonListener(this@PriceBarFragment)
            stfRangeBar.setEntries(barEntrys)
            stfRangeBar.onRangeChanged = { leftPinValue, rightPinValue ->
                val minPrice = if (leftPinValue == "0") "1" else leftPinValue
                priceRangeStringBuilder.append("₩")
                priceRangeStringBuilder.append(minPrice)
                priceRangeStringBuilder.append(",000")
                priceRangeStringBuilder.append(" - ")
                priceRangeStringBuilder.append("₩")
                priceRangeStringBuilder.append(rightPinValue)
                priceRangeStringBuilder.append(",000")
                priceRangeStringBuilder.append(" + ")
                priceRange = CustomText(priceRangeStringBuilder.toString())
                priceRangeStringBuilder.clear()
                minPriceValue = CustomText(leftPinValue ?: "1,000")
                maxPriceValue = CustomText(rightPinValue ?: "1,000,000+")
                customBar.setNextFragmentButtonListener(this@PriceBarFragment)
            }

        }
    }

    companion object {
        private const val INITIAL_VALUE = 0f
    }

    override fun goBackBefore() {
        calendarPopUp.setUpDefaultCalendar()
    }

    override fun goNextFragment() {
        findNavController().navigate(R.id.action_priceBar_to_residentsCountsFragment)
    }
}