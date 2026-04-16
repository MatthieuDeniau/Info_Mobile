package com.example.table.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class GetPreviousPeriodUseCaseTest {

    private val useCase = GetPreviousPeriodUseCase()

    @Test
    fun `revenir a la periode precedente doit soustraire 7 jours`() {
        val date = LocalDate.of(2024, 10, 21)
        val expected = LocalDate.of(2024, 10, 14)
        
        val result = useCase(date)
        
        assertEquals(expected, result)
    }
}
