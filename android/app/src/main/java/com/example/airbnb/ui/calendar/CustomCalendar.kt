package com.example.airbnb.ui.calendar

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.airbnb.R
import com.example.airbnb.ui.custom.datepicker.CalendarConstraints
import com.example.airbnb.ui.custom.datepicker.DateValidatorPointForward
import com.example.airbnb.ui.custom.datepicker.MaterialDatePicker
import com.example.airbnb.ui.home.HomeFragmentDirections

class CustomCalendar(private val fragmentID: Fragment, private val action: Int?) {

    fun setUpDefaultCalendar() {
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
//                .setSelection()
                .setCalendarConstraints(constraintsBuilder.build())
                .setTheme(R.style.CalendarTheme)
                .build()

        dateRangePicker.addOnPositiveButtonClickListener {
            if (action != null) {
                findNavController(fragmentID).navigate(action)
            }
        }
        dateRangePicker.show(fragmentID.childFragmentManager, "CALENDAR")
    }

}