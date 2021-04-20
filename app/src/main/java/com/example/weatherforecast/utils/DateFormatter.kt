package com.example.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    const val DAYWEEK_MONTH_DAY = "E, MMM dd"
    val dayDayMonth = SimpleDateFormat(DAYWEEK_MONTH_DAY, Locale.ENGLISH)
    fun formatLongToDay(dt: Long): String = dayDayMonth.format(Date(dt))
}