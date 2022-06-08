package com.example.airbnb.data

import androidx.core.util.Pair
import com.example.airbnb.common.timestampToDateString
import com.example.airbnb.dto.SearchFilterDto
import java.io.Serializable

data class SearchFilter(
    val location: String,
    var checkInOut: Pair<Long, Long>?,
    val guests: Int?,
    var priceRange: Pair<Int, Int>?
) : Serializable

fun SearchFilter.toDto(): SearchFilterDto {
    val checkIn = if (checkInOut == null) null else timestampToDateString(checkInOut!!.first)
    val checkOut = if (checkInOut == null) null else timestampToDateString(checkInOut!!.second)
    val minPrice = priceRange?.first
    val maxPrice = priceRange?.second

    return SearchFilterDto(
        location,
        checkIn,
        checkOut,
        guests,
        minPrice,
        maxPrice
    )
}
