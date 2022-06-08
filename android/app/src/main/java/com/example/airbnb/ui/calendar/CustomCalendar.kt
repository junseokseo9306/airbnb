package com.example.airbnb.ui.calendar

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.airbnb.R
import com.example.airbnb.ui.custom.datepicker.CalendarConstraints
import com.example.airbnb.ui.custom.datepicker.DateValidatorPointForward
import com.example.airbnb.ui.custom.datepicker.MaterialDatePicker
import androidx.core.util.Pair
import androidx.navigation.NavDirections
import com.example.airbnb.data.SearchFilter

class CustomCalendar(private val fragmentID: Fragment, private val action: Int?, private val reservationDetail: SearchFilter) {

    fun setUpDefaultCalendar() {
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setSelection(reservationDetail.checkInOut)
                .setCalendarConstraints(constraintsBuilder.build())
                .setTheme(R.style.CalendarTheme)
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            if (action != null) {
                reservationDetail.checkInOut = it
                val bundle = bundleOf("reservationDates" to reservationDetail)
                findNavController(fragmentID).navigate(action, bundle)
            }
        }
        dateRangePicker.show(fragmentID.childFragmentManager, "CALENDAR")
    }


}