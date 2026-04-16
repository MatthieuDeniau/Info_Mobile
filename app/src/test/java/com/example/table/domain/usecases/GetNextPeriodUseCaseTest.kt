package com.example.table.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class GetNextPeriodUseCaseTest {

    private val useCase = GetNextPeriodUseCase()

    @Test
    fun `passer a la periode suivante doit ajouter 7 jours`() {
        val date = LocalDate.of(2024, 10, 21)
        val expected = LocalDate.of(2024, 10, 28)
        
        val result = useCase(date)
        
        assertEquals(expected, result)
    }
}
