package com.example.airbnb.repository

import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.PriceRangeDto
import com.example.airbnb.dto.SearchFilterDto
import com.example.airbnb.model.City

interface AirbnbRepository {

    suspend fun getCityList(): List<City>

    suspend fun getAccommodations(searchFilter: SearchFilterDto): AccommodationDto

    suspend fun getPriceRange(
        location: String,
        startDate: String?,
        endDate: String?
    ): PriceRangeDto
}