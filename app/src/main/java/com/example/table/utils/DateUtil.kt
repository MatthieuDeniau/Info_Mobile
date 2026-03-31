package com.example.table.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val QUEBEC_ZONE_ID = ZoneId.of("America/Toronto")

data class Week(
    val start: LocalDate,
    val end: LocalDate
)

fun currentWeek(): Week {
    val today = LocalDate.now(QUEBEC_ZONE_ID)
    val start = today.with(DayOfWeek.MONDAY)
    val end = start.plusDays(6)
    return Week(start, end)
}

fun Week.previous(): Week {
    val newStart = start.minusWeeks(1)
    return Week(newStart, newStart.plusDays(6))
}

fun Week.next(): Week {
    val newStart = start.plusWeeks(1)
    return Week(newStart, newStart.plusDays(6))
}

fun Week.format(): String {
    val dayFormatter = DateTimeFormatter.ofPattern("dd")
    val monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.FRENCH)

    val startDay = start.format(dayFormatter)
    val endDay = end.format(dayFormatter)
    val month = start.format(monthFormatter).replaceFirstChar { it.uppercase() }

    return "$startDay - $endDay $month"
}

fun Week.days(): List<LocalDate> {
    return (0..6).map { start.plusDays(it.toLong()) }
}
