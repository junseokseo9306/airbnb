package com.example.airbnb.datasource

import android.util.Log
import com.example.airbnb.dto.CityDto
import com.example.airbnb.network.AirbnbApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeDataSourceImpl @Inject constructor(private val api: AirbnbApi): HomeDataSource {

    override suspend fun getHomeContents(): Flow<CityDto> {
        val result = api.getHomeContents()
        Log.d("datasource", result.cities?.size.toString())

        return flow { emit(result) }
    }
}