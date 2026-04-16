package com.example.table.domain.usecases

import org.junit.Assert.assertEquals
import org.junit.Test

class GetSlotLabelUseCaseTest {

    private val useCase = GetSlotLabelUseCase()

    @Test
    fun `doit retourner le bon label pour chaque slot`() {
        assertEquals("Petit-déjeuner", useCase(1))
        assertEquals("Déjeuner", useCase(2))
        assertEquals("En-cas", useCase(3))
        assertEquals("Dîner", useCase(4))
        assertEquals("Repas", useCase(5))
    }
}
