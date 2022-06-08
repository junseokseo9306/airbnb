package com.example.airbnb.ui.pricebar

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.common.CustomViewClick
import com.example.airbnb.data.CustomText
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentPricebarBinding
import com.example.airbnb.ui.calendar.CustomCalendar
import com.stfalcon.pricerangebar.model.BarEntry
import java.text.SimpleDateFormat
import kotlin.text.StringBuilder

class PriceBarFragment() : Fragment(), CustomViewClick {

    private lateinit var binding: FragmentPricebarBinding
    private lateinit var calendarPopUp: CustomCalendar
    private lateinit var reservationDetail: SearchFilter

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

        reservationDetail = arguments?.getSerializable("reservationDates") as SearchFilter
        Log.d("PriceBar", reservationDetail.checkInOut.toString())

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

                if (leftPinValue != null && rightPinValue != null) {
                    val min = leftPinValue.toInt() * 1000
                    val max = rightPinValue.toInt() * 1000
                    reservationDetail.priceRange = Pair(min, max)
                } else {
                    val min = 1000
                    val max = 1000000
                    reservationDetail.priceRange = Pair(min, max)
                }
                customBar.setNextFragmentButtonListener(this@PriceBarFragment)
            }

        }
    }

    companion object {
        private const val INITIAL_VALUE = 0f
    }

    override fun goBackBefore() {
        val action = R.id.action_priceBarFragment_self2
        calendarPopUp = CustomCalendar(this, action, reservationDetail)
        calendarPopUp.setUpDefaultCalendar()
    }

    override fun goNextFragment() {
        val bundle = bundleOf("reservationDetail" to reservationDetail)
        findNavController().navigate(R.id.action_priceBarFragment_to_residentsCountsFragment, bundle)
    }
}