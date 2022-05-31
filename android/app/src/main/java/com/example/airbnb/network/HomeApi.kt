package com.example.airbnb.network

import com.example.airbnb.dto.CityDto
import retrofit2.http.GET

interface HomeApi {

    @GET("/api/nearMeCities")
    suspend fun getHomeContents() : CityDto

}