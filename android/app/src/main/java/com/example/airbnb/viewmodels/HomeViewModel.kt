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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val tmapRepository: TmapRepository
) : ViewModel() {

    private val _myLongitude: MutableStateFlow<Double> = MutableStateFlow(0.0)

    private val _myLatitude: MutableStateFlow<Double> = MutableStateFlow(0.0)

    private val _cityInfo: MutableStateFlow<MutableList<CityInfo>> =
        MutableStateFlow(mutableListOf())
    val cityInfo = _cityInfo.asStateFlow()

    fun loadContents() {
        viewModelScope.launch {
            val cityList = homeRepository.loadHomeContents()
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
                delay(500)
                tmapRepository.getTime(
                    TmapRequest(
                        myLongitude,
                        myLatitude,
                        city.currentCoordinate.latitude,
                        city.currentCoordinate.longitude
                    )
                )
            }.collectIndexed { index, tmap ->
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
