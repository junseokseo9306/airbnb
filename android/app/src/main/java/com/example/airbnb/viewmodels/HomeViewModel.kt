package com.example.airbnb.viewmodels

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.model.City
import com.example.airbnb.network.TmapRequest
import com.example.airbnb.repository.HomeRepository
import com.example.airbnb.repository.TmapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _homeContents: MutableStateFlow<MutableList<City>> = MutableStateFlow(mutableListOf())
    val homeContent: StateFlow<List<City>> = _homeContents.asStateFlow()

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

            Log.d("viewModel", "cities size" + citiesCoordinate.size.toString())
            Log.d("viewModel", "myLongitude : ${citiesCoordinate[0].longitude}, myLatitude : ${citiesCoordinate[0].latitude}")
            Log.d("viewModel", "myLongitude : ${myLongitude.value}, myLatitude : ${myLatitude.value}")
            citiesCoordinate.removeAt(0)
//            getTimeToCity(myLongitude.value, myLatitude.value, citiesCoordinate)
        }
    }

    //포스트맨 api의 longitude, latitude의 순서가 뒤바뀌어있
    private fun getTimeToCity(
        myLongitude: Double,
        myLatitude: Double,
        cityCoords: List<City.Coordinate>
    ) {
        viewModelScope.launch {
            cityCoords.forEach { eachCoord ->
                launch {
                    _cityTime.setList(
                        tmapRepository.getTime(
                            TmapRequest(
                                myLongitude,
                                myLatitude,
                                eachCoord.latitude,
                                eachCoord.longitude
                            )
                        ).totalMinute)
                }.join()
            }
            Log.d("viewModel", "_cityTimesize ${_cityTime.value.size}")
        }
    }

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