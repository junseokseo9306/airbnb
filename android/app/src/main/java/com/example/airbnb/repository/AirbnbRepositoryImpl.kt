package com.example.airbnb.repository

import com.example.airbnb.datasource.AirbnbDataSource
import com.example.airbnb.dto.SearchFilterDto
import com.example.airbnb.dto.toCity
import com.example.airbnb.model.City
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirbnbRepositoryImpl @Inject constructor(private val dataSource: AirbnbDataSource) :
    AirbnbRepository {

    override suspend fun getCityList(): List<City> =
        dataSource.getCityDto().toCity()

    override suspend fun getAccommodations(searchFilter: SearchFilterDto) =
        dataSource.getAccommodations(searchFilter, Dispatchers.Default)
}