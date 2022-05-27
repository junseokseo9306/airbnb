package com.example.airbnb.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.data.CityInfo
import com.example.airbnb.model.City
import com.example.airbnb.network.TmapRequest
import com.example.airbnb.repository.HomeRepository
import com.example.airbnb.repository.TmapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val tmapRepository: TmapRepository
) : ViewModel() {

    private val _homeContents: MutableStateFlow<MutableList<City>> =
        MutableStateFlow(mutableListOf())
    val homeContent = _homeContents.asStateFlow()

    private val _cityTime: MutableStateFlow<MutableList<Int>> = MutableStateFlow(mutableListOf())
    val cityTime = _cityTime.asStateFlow()

    private val _myLongitude: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val myLongitude = _myLongitude.asStateFlow()

    private val _myLatitude: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val myLatitude = _myLatitude.asStateFlow()

    private val _citiesCoord: MutableStateFlow<List<City.Coordinate>> = MutableStateFlow(
        mutableListOf()
    )
    val citiesCoord = _citiesCoord.asStateFlow()

    private val _cityInfo: MutableStateFlow<MutableList<CityInfo>> =
        MutableStateFlow(mutableListOf())
    val cityInfo = _cityInfo.asStateFlow()

    fun loadContents() {
        viewModelScope.launch {
            launch {
                homeRepository.loadHomeContents().collect { cities ->
                    _homeContents.value = cities.toMutableList()
                }
            }.join()
            val citiesCoordinate = _homeContents.value.map { city ->
                City.Coordinate(city.currentCoordinate.latitude, city.currentCoordinate.longitude)
            }.toMutableList()

            getTimeToCity(myLongitude.value, myLatitude.value, citiesCoordinate)
        }
    }

    //포스트맨 api의 longitude, latitude의 순서가 뒤바뀌어있
    private fun getTimeToCity(
        myLongitude: Double,
        myLatitude: Double,
        cityCoords: List<City.Coordinate>
    ) {
        viewModelScope.launch {
            val start = System.currentTimeMillis()
            for (index in cityCoords.indices) {
                tmapRepository.getTime(
                    TmapRequest(
                        myLongitude,
                        myLatitude,
                        cityCoords[index].latitude,
                        cityCoords[index].longitude
                    )
                ).buffer().onEach {
                    _cityInfo.setList(
                        CityInfo(
                            homeContent.value[index],
                            it.totalMinute
                        )
                    )
                }.launchIn(this)
            }
            val end = System.currentTimeMillis()
            Log.d("viewModel", "${(end - start) / 100}")
        }
    }

    //helper function

    fun setMyLocation(latitude: Double, longitude: Double) {
        _myLatitude.value = latitude
        _myLongitude.value = longitude
    }

    private fun <E> MutableStateFlow<MutableList<E>>.setList(element: E?) {
        val tempList: MutableList<E> = mutableListOf()
        this.value.let { tempList.addAll(it) }
        if (element != null) {
            tempList.add(element)
        }
        this.value = tempList
    }
}