package com.example.airbnb.ui.pricebar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.data.CustomText
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentPricebarBinding
import com.example.airbnb.ui.calendar.CustomCalendar
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class PriceBarFragment() : Fragment() {

    private lateinit var binding: FragmentPricebarBinding
    private lateinit var calendarPopUp: CustomCalendar
    private lateinit var searchFilter: SearchFilter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pricebar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFilter = arguments?.getSerializable("reservationDates") as SearchFilter
        with(binding) {
            customBar.isValid(true)
            customBar.setBackClickListener {
                goBackBefore()
            }
            customBar.setNextFragmentButtonClickListener {
                goNextFragment()
            }
        }
    }

    private fun setPriceRangeBarText(
        minPrice: Int,
        maxPrice: Int
    ) = with(binding) {
        rangeSlider.values = listOf(minPrice.toFloat(), maxPrice.toFloat())
        rangeSlider.valueFrom = minPrice.toFloat()
        rangeSlider.valueTo = maxPrice.toFloat()
        setCustomBarPriceText(minPrice, maxPrice)

        binding.rangeSlider.addOnChangeListener { slider, _, _ ->
            setCustomBarPriceText(slider.values[0].toInt(), slider.values[1].toInt())
        }
    }

    private fun setCustomBarPriceText(minPrice: Int, maxPrice: Int) {
        val currencyMinPrice = getMoneyFormat(minPrice)
        val currencyMaxPrice = getMoneyFormat(maxPrice)

        binding.customBar.setSubTitleText(
            getString(
                R.string.price_range,
                currencyMinPrice,
                currencyMaxPrice
            )
        )

        binding.tvMinPriceView.setText(currencyMinPrice)
        binding.tvMaxPriceView.setText(currencyMaxPrice)
    }

    private fun getMoneyFormat(money: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        return format.format(money)
    }

    private fun goBackBefore() {
        val action = R.id.action_priceBarFragment_self2
        calendarPopUp = CustomCalendar(this, action, searchFilter)
        calendarPopUp.setUpDefaultCalendar()
    }

    private fun goNextFragment() {
        val bundle = bundleOf("reservationDetail" to searchFilter)
        findNavController().navigate(
            R.id.action_priceBarFragment_to_residentsCountsFragment,
            bundle
        )
    }
}