package com.example.airbnb.data

data class SearchFilter(
    val location: String,
    val checkIn: Long?,
    val checkOut: Long?,
    val guests: Int?,
    val minPrice: Int?,
    val maxPrice: Int?
)
