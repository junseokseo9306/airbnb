package com.example.airbnb.dto

import com.example.airbnb.model.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("cities")
    val cities: List<Local>?
) {
    @Serializable
    data class Local(
        @SerialName("cityName")
        val cityName: String?,
        @SerialName("coord")
        val coordinate: Coordinate?,
        @SerialName("image")
        val image: String?
    )

    @Serializable
    data class Coordinate(
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
                cityDto.coordinate?.latitude ?: 0.0,
                cityDto.coordinate?.longitude ?: 0.0
            )
        )
    }
}
