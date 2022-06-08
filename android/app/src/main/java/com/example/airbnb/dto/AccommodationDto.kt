package com.example.airbnb.dto

data class AccommodationDto(
    val count: Int,
    val accommodations: List<Accommodation>
) {
    data class Accommodation(
        val name: String,
        val price: Int,
        val rating: Double,
        val reviews: Int,
        val isWish: Boolean,
        val isSuperHost: Boolean,
        val thumbnail: String
    )
}
