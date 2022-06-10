package com.example.airbnb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Accommodation(
    val id: Int,
    @SerialName("accomName")
    val name: String,
    val price: Int,
    val totalPrice: Int,
    val rating: Double,
    val reviews: Int,
    @SerialName("wish")
    val isWish: Boolean,
    @SerialName("superHost")
    val isSuperHost: Boolean,
    val thumbnail: String,
    val latitude: Double,
    val longitude: Double
): java.io.Serializable
