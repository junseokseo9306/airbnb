package com.example.airbnb.data

data class SearchFilter(
    val location: String,
    val checkInOut: Pair<Long, Long>?,
    val guests: Int?,
    val priceRange: Pair<Int, Int>?
)
