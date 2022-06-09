package com.example.airbnb.datasource

import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.CityDto
import com.example.airbnb.dto.SearchFilterDto
import kotlinx.coroutines.CoroutineDispatcher

interface AirbnbDataSource {

    suspend fun getCityDto(): CityDto

    suspend fun getAccommodations(
        searchFilterDto: SearchFilterDto,
        dispatcher: CoroutineDispatcher
    ): AccommodationDto
}