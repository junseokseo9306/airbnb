package com.example.airbnb.dto

import com.example.airbnb.model.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("cities")
    val cities: List<CityLocal>?
) {
    @Serializable
    data class CityLocal(
        @SerialName("cityName")
        val cityName: String?,
        @SerialName("coord")
        val coord: Coord?,
        @SerialName("image")
        val image: String?
    )

    @Serializable
    data class Coord(
        @SerialName("latitude")
        val latitude: Double?,
        @SerialName("longitude")
        val longitude: Double?
    )
}

fun CityDto.toCity(): List<City> {
    val cities = requireNotNull(cities)

    return cities.map { cityDto ->
        City(
            cityName = requireNotNull(cityDto.cityName),
            image = cityDto.image.orEmpty(),
            currentCoordinate = City.Coordinate(
                cityDto.coord?.latitude ?: 0.0,
                cityDto.coord?.longitude ?: 0.0
            )
        )
    }
}
