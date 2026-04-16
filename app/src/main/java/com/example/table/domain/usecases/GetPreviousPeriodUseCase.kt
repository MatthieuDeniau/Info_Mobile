package com.example.table.domain.usecases

import java.time.LocalDate
import javax.inject.Inject

class GetPreviousPeriodUseCase @Inject constructor() {
    operator fun invoke(startDate: LocalDate): LocalDate {
        return startDate.minusDays(7)
    }
}
