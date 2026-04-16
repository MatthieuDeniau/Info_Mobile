package com.example.table.domain.usecases

import java.time.LocalDate
import javax.inject.Inject

class GetNextPeriodUseCase @Inject constructor() {
    operator fun invoke(startDate: LocalDate): LocalDate {
        return startDate.plusDays(7)
    }
}
