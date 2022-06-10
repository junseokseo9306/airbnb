package com.example.airbnb.common

import java.text.SimpleDateFormat
import java.util.*

private fun initFormat(pattern: String): SimpleDateFormat {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    format.isLenient = false

    return format
}

fun timestampToDateString(timestamp: Long, pattern: String = "yyyy-MM-dd"): String {
    val format = initFormat(pattern)
    return format.format(Date(timestamp))
}

fun dateStringToTimestamp(dateString: String, pattern: String = "yyyy-MM-dd"): Long {
    val format = initFormat(pattern)
    val date = format.parse(dateString)
    return date?.time ?: 0
}
