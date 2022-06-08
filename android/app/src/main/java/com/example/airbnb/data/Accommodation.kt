package com.example.airbnb.data

data class Accommodation(
    val id: Int,
    val name: String,
    val price: Int,
    val rating: Double,
    val reviews: Int,
    val isWish: Boolean,
    val isSuperHost: Boolean,
    val thumbnail: String
)
