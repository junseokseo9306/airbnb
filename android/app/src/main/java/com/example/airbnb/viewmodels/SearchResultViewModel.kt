package com.example.airbnb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.data.Accommodation
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.data.toDto
import com.example.airbnb.repository.AirbnbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val repository: AirbnbRepository
) : ViewModel() {

    private val _accommodationsList = MutableStateFlow(emptyList<Accommodation>())
    val accommodationList = _accommodationsList.asStateFlow()

    fun getAccommodations(searchFilter: SearchFilter) {
        viewModelScope.launch {
            val accommodationDto = repository.getAccommodations(searchFilter.toDto())
            _accommodationsList.value = accommodationDto.accommodations
        }
    }
}