package com.example.airbnb.viewmodels

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import com.example.airbnb.R
import com.example.airbnb.ui.custom.CustomResidentClickView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResidentCountsViewModel : ViewModel(), CustomResidentClickView.OnChangeClickCountListener {

    private var _totalAdultCount = MutableStateFlow(0)
    val totalAdultCount = _totalAdultCount.asStateFlow()

    private var _totalBabyCount = MutableStateFlow(0)
    val totalBabyCount = _totalBabyCount.asStateFlow()

    private var _adultCount = MutableStateFlow(0)
    val adultCount = _adultCount.asStateFlow()

    private var _kidsCount = MutableStateFlow(0)
    val kidsCount = _kidsCount.asStateFlow()

    private var _babyCount = MutableStateFlow(0)
    val babyCount = _babyCount.asStateFlow()

    override fun onChanged(@IdRes id: Int, step: Int) {
        when (id) {
            R.id.cv_adult -> {
                _adultCount.value += step
                _totalAdultCount.value += step
            }
            R.id.cv_kids -> {
                _kidsCount.value += step
                _totalAdultCount.value += step
            }
            R.id.cv_baby -> {
                _babyCount.value += step
                _totalBabyCount.value += step
            }
        }
    }
}