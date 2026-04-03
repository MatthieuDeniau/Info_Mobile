package com.example.table.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class FormatPeriodLabelUseCaseTest {
    private lateinit var useCase: FormatPeriodLabelUseCase

    @Before
    fun setup() {
        useCase = FormatPeriodLabelUseCase()
    }

    @Test
    fun `formater une periode du milieu du mois doit etre correct`() {
        // Arrange
        val date = LocalDate.of(2024, 5, 20)

        // Act
        val result = useCase(date)

        // Assert
        assertEquals("20 - 26 mai", result)
    }

    @Test
    fun `formater une periode a cheval sur deux mois doit afficher le mois de fin`() {
        // Arrange
        val date = LocalDate.of(2024, 5, 27) // Lundi 27 Mai

        // Act
        val result = useCase(date)

        // Assert
        // 27 Mai au 2 Juin -> "27 - 02 juin"
        assertEquals("27 - 02 juin", result)
    }

    @Test
    fun `formater une periode avec changement d'annee doit etre correct`() {
        // Arrange
        val date = LocalDate.of(2024, 12, 30) // Lundi 30 Décembre

        // Act
        val result = useCase(date)

        // Assert
        // 30 Décembre au 5 Janvier -> "30 - 05 janvier"
        assertEquals("30 - 05 janvier", result)
    }
}
