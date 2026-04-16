package com.example.table.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class FormatPeriodLabelUseCaseTest {

    private val useCase = FormatPeriodLabelUseCase()

    @Test
    fun `le libelle de la periode doit etre formate correctement`() {
        val date = LocalDate.of(2024, 10, 21) // Un lundi par exemple
        val result = useCase(date)
        // 21 - 27 octobre
        assertEquals("21 - 27 octobre", result)
    }

    @Test
    fun `le libelle doit gerer le changement de mois`() {
        val date = LocalDate.of(2024, 10, 28)
        val result = useCase(date)
        // 28 - 03 novembre
        assertEquals("28 - 03 novembre", result)
    }
}
