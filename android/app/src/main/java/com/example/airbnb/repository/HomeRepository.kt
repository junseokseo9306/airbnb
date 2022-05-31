package com.example.airbnb.repository

import com.example.airbnb.model.City
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun loadHomeContents(): Flow<List<City>>

}