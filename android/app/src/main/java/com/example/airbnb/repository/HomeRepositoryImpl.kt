package com.example.airbnb.repository

import com.example.airbnb.datasource.HomeDataSource
import com.example.airbnb.dto.toCity
import com.example.airbnb.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(private val dataSource: HomeDataSource) :
    HomeRepository {

    override suspend fun loadHomeContents(): Flow<List<City>> =
        dataSource.getHomeContents().map { homeContentDTO ->
            homeContentDTO.toCity()
        }
}