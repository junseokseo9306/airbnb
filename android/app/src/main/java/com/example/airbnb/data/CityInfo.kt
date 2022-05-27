package com.example.airbnb.data

import com.example.airbnb.model.City

data class Image(
    val imageUrl: String,
)

data class CityInfo(
    val city: City,
    val travelTime: Int = 30
)