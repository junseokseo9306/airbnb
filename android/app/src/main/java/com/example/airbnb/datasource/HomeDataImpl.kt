package com.example.airbnb.datasource

import android.util.Log
import com.example.airbnb.dto.CityDto
import com.example.airbnb.network.HomeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeDataImpl @Inject constructor(private val api: HomeApi): HomeDataSource {

    override suspend fun getHomeContents(): Flow<CityDto> {
        val result = api.getHomeContents()
        Log.d("datasource", result.cities?.size.toString())

        return flow { emit(result) }
    }

}