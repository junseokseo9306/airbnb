package com.example.airbnb.model

data class City (
    val cityName: String,
    val image: String,
    val currentCoordinate: Coordinate
) {
    data class Coordinate(
        val latitude: Double,
        val longitude: Double
    )
}