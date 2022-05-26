package com.example.airbnb.dto

import com.example.airbnb.model.Tmap

data class TmapDto(
    val features: List<Feature>
)

data class Properties(
    val totalTime: Int
)

data class Feature(
    val properties: Properties
)

fun TmapDto.toTmap(): Tmap {
    return Tmap(
        features[0].properties.totalTime / 60
    )
}