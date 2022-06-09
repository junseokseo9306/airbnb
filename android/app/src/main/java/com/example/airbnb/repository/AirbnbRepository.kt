package com.example.airbnb.repository

import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.SearchFilterDto
import com.example.airbnb.model.City

interface AirbnbRepository {

    suspend fun getCityList(): List<City>

    suspend fun getAccommodations(searchFilter: SearchFilterDto): AccommodationDto
}