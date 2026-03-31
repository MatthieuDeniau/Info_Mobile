package com.example.table.domain.usecases

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

class FormatPeriodLabelUseCase @Inject constructor() {
    operator fun invoke(startDate: LocalDate): String {
        val endDate = startDate.plusDays(6)
        fun Int.twoDigits(): String = this.toString().padStart(2, '0')
        fun LocalDate.monthLabel(): String =
            this.month.getDisplayName(TextStyle.FULL, Locale.FRENCH)

        return "${startDate.dayOfMonth.twoDigits()} - ${endDate.dayOfMonth.twoDigits()} ${endDate.monthLabel()}"
    }
}
