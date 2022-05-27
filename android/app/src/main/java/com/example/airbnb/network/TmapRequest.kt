package com.example.airbnb.network

import kotlinx.serialization.Serializable

@Serializable
data class TmapRequest(
    val startX: Double,
    val startY: Double,
    val endX: Double,
    val endY: Double
)
