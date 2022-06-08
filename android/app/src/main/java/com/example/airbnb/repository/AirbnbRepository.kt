package com.example.airbnb.repository

import com.example.airbnb.model.City
import kotlinx.coroutines.flow.Flow

interface AirbnbRepository {

    suspend fun loadHomeContents(): List<City>
}