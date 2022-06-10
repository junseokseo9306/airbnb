package com.example.airbnb.datasource

import com.example.airbnb.data.Accommodation
import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.CityDto
import com.example.airbnb.dto.PriceRangeDto
import com.example.airbnb.dto.SearchFilterDto
import com.example.airbnb.network.AirbnbApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirbnbDataSourceImpl @Inject constructor(private val api: AirbnbApi) : AirbnbDataSource {

    override suspend fun getCityDto(): CityDto {
        return api.getCityDto()
    }

    override suspend fun getAccommodations(
        searchFilterDto: SearchFilterDto,
        dispatcher: CoroutineDispatcher
    ): AccommodationDto {
        return withContext(dispatcher) {
            api.getAccommodations(
                searchFilterDto.location,
                searchFilterDto.checkIn,
                searchFilterDto.checkOut,
                searchFilterDto.guests,
                searchFilterDto.minPrice,
                searchFilterDto.maxPrice
            )
        }
    }

    // TODO : Price Range API Request
    override suspend fun getPriceRange(
        location: String,
        startDate: String?,
        endDate: String?
    ): PriceRangeDto {

        return PriceRangeDto(
            10000,
            100000
        )
    }
}