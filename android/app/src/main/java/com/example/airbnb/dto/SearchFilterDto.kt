package com.example.airbnb.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchFilterDto(
    val location: String,
    val checkIn: String?,
    val checkOut: String?,
    val guests: Int?,
    val minPrice: Int?,
    val maxPrice: Int?
)
