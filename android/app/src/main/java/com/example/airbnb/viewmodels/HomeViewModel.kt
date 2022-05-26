package com.example.airbnb.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.model.City
import com.example.airbnb.network.TmapRequest
import com.example.airbnb.repository.HomeRepository
import com.example.airbnb.repository.TmapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val tmapRepository: TmapRepository
) : ViewModel() {

    private val _homeContents: MutableStateFlow<List<City>> = MutableStateFlow(mutableListOf())
    val homeContent: StateFlow<List<City>> = _homeContents.asStateFlow()

    private val _cityTime: MutableStateFlow<Int> = MutableStateFlow(0)
    val cityTime = _cityTime.asStateFlow()

    fun loadHomeContents() {
        viewModelScope.launch {
            homeRepository.loadHomeContents().collect { cities ->
                Log.d("viewModel", cities.size.toString())
                _homeContents.value = cities
                Log.d("viewModel", _homeContents.value[0].cityName)
            }
        }
    }

    fun getTimeToCity(
        myCoordinate: City.Coordinate,
        cityCoordinate: City.Coordinate
    ) {
        viewModelScope.launch {
            val tmapData = tmapRepository.getTime(
                TmapRequest(
                    myCoordinate.latitude,
                    myCoordinate.longitude,
                    cityCoordinate.latitude,
                    cityCoordinate.longitude
                )
            )

            _cityTime.value = tmapData.totalMinute
        }
    }
}