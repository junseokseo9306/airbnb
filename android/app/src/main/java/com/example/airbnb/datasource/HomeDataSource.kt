package com.example.airbnb.datasource

import com.example.airbnb.dto.CityDto
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    suspend fun getHomeContents(): Flow<CityDto>

}