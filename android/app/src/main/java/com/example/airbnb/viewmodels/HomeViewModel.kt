package com.example.airbnb.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.data.CityInfo
import com.example.airbnb.model.City
import com.example.airbnb.network.TmapRequest
import com.example.airbnb.repository.AirbnbRepository
import com.example.airbnb.repository.TmapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val airbnbRepository: AirbnbRepository,
    private val tmapRepository: TmapRepository
) : ViewModel() {

    private val _myLongitude: MutableStateFlow<Double> = MutableStateFlow(0.0)

    private val _myLatitude: MutableStateFlow<Double> = MutableStateFlow(0.0)

    private val _cityInfo: MutableStateFlow<MutableList<CityInfo>> =
        MutableStateFlow(mutableListOf())
    val cityInfo = _cityInfo.asStateFlow()

    init {
        setMyLocation(DEFAULT_LOCATION_LATITUDE, DEFAULT_LOCATION_LONGITUDE)
    }

    fun getCityList() {
        viewModelScope.launch {
            val cityList = airbnbRepository.getCityList()
            getTimeToCity(_myLongitude.value, _myLatitude.value, cityList)
            Log.d("viewModel", "mylongitude ${_myLongitude.value} my latitude ${_myLatitude.value}")
        }
    }

    @OptIn(FlowPreview::class)
    private fun getTimeToCity(
        myLongitude: Double,
        myLatitude: Double,
        cityList: List<City>
    ) {
        viewModelScope.launch {
            val start = System.currentTimeMillis()

            cityList.asFlow().flatMapMerge { city ->
                delay(1000)
                tmapRepository.getTime(
                    TmapRequest(
                        myLongitude,
                        myLatitude,
                        city.currentCoordinate.latitude,
                        city.currentCoordinate.longitude
                    )
                )
            }.buffer().collectIndexed { index, tmap ->
                _cityInfo.setList(
                    CityInfo(
                        cityList[index],
                        tmap.totalMinute
                    )
                )
            }
            val end = System.currentTimeMillis()
            Log.d("viewModel", "${(end - start)} 초")
        }
    }

    //helper function
    fun setMyLocation(latitude: Double, longitude: Double) {
        _myLatitude.value = latitude
        _myLongitude.value = longitude
    }

    companion object {
        private const val DEFAULT_LOCATION_LATITUDE = 37.37599
        private const val DEFAULT_LOCATION_LONGITUDE = 127.132685

        private fun <E> MutableStateFlow<MutableList<E>>.setList(element: E?) {
            val tempList: MutableList<E> = mutableListOf()
            this.value.let { tempList.addAll(it) }
            if (element != null) {
                tempList.add(element)
            }
            this.value = tempList
        }
    }

}
