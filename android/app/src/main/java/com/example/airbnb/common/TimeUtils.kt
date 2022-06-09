package com.example.airbnb.common

import java.text.SimpleDateFormat
import java.util.*

private fun initFormat(): SimpleDateFormat {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    format.isLenient = false

    return format
}

fun timestampToDateString(timestamp: Long): String {
    val format = initFormat()
    return format.format(Date(timestamp))
}

fun dateStringToTimestamp(dateString: String): Long {
    val format = initFormat()
    val date = format.parse(dateString)
    return date?.time ?: 0
}
