package com.example.airbnb.repository

import com.example.airbnb.datasource.AirbnbDataSource
import com.example.airbnb.dto.toCity
import com.example.airbnb.model.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirbnbRepositoryImpl @Inject constructor(private val dataSource: AirbnbDataSource) :
    AirbnbRepository {

    override suspend fun loadHomeContents(): List<City> =
        dataSource.getHomeContents().toCity()
}