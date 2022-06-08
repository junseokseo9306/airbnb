package com.example.airbnb.datasource

import com.example.airbnb.dto.CityDto
import kotlinx.coroutines.flow.Flow

interface AirbnbDataSource {

    suspend fun getHomeContents(): CityDto
}