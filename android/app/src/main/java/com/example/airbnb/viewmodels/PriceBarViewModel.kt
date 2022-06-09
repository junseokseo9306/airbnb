package com.example.airbnb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.repository.AirbnbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PriceBarViewModel @Inject constructor(
    private val repository: AirbnbRepository
) : ViewModel() {

    private val _priceRange = MutableStateFlow(Pair(0, 0))
    val priceRange = _priceRange.asStateFlow()

    fun getPriceRange(location: String, startDate: String?, endDate: String?) {
        viewModelScope.launch {
            val range = repository.getPriceRange(
                location,
                startDate,
                endDate
            )

            _priceRange.value = Pair(range.minPrice, range.maxPrice)
        }
    }
}