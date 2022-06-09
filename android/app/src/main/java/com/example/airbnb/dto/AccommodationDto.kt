package com.example.airbnb.dto

import com.example.airbnb.data.Accommodation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccommodationDto(
    val count: Int,
    @SerialName("accommodations")
    val accommodations: List<Accommodation>
)
