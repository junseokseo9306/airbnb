package com.example.airbnb.network

import com.example.airbnb.dto.AccommodationDto
import com.example.airbnb.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AirbnbApi {

    @GET("/api/nearMeCities")
    suspend fun getCityDto(): CityDto

    @GET("/api/accommodations")
    suspend fun getAccommodations(
        @Query("location") location: String,
        @Query("checkIn") checkIn: String?,
        @Query("checkOut") checkOut: String?,
        @Query("guests") guests: Int?,
        @Query("minPrice") minPrice: Int?,
        @Query("maxPrice") maxPrice: Int?
    ): AccommodationDto
}