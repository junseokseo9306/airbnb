package com.example.airbnb.dto

import com.example.airbnb.model.Tmap
import kotlinx.serialization.Serializable

@Serializable
data class TmapDto(
    val features: List<Feature>?
)

@Serializable
data class Properties(
    val totalTime: Int?
)

@Serializable
data class Feature(
    val properties: Properties?
)

fun TmapDto.toTmap(): Tmap {
    return Tmap(
        requireNotNull(
            features?.get(0)?.properties?.totalTime?.div(60)
        )
    )
}