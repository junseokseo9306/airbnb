package com.example.airbnb.data

import java.io.Serializable
import androidx.core.util.Pair

data class SearchFilter(
    val location: String,
    var checkInOut: Pair<Long, Long>?,
    var guests: Int?,
    var priceRange: Pair<Int, Int>?
): Serializable
