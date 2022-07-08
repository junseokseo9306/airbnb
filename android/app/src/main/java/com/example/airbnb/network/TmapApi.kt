package com.example.airbnb.network

import com.example.airbnb.dto.TmapDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TmapApi {

    @POST("routes?version=1&format=json&callback=result")
    suspend fun getTime(
        @Header("appKey") appKey: String,
        @Body body: TmapRequest,
    ): TmapDto
}