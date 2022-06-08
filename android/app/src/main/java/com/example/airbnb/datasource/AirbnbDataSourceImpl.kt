package com.example.airbnb.datasource

import com.example.airbnb.dto.CityDto
import com.example.airbnb.network.AirbnbApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirbnbDataSourceImpl @Inject constructor(private val api: AirbnbApi): AirbnbDataSource {

    override suspend fun getHomeContents(): CityDto {
        return api.getHomeContents()
    }
}