package com.example.table.domain.usecases

import com.example.table.presentation.RecipeVM
import org.junit.Assert.assertEquals
import org.junit.Test

class SortRecipesByLastUsedTest {
    private val useCase = SortRecipesByLastUsedUseCase()

    @Test
    fun `le tri doit mettre les recettes jamais faites en premier`() {
        val r1 = RecipeVM(id = 1, name = "A", lastMade = "2023-01-01")
        val r2 = RecipeVM(id = 2, name = "B", lastMade = null)
        val r3 = RecipeVM(id = 3, name = "C", lastMade = "2022-01-01")

        val result = useCase(listOf(r1, r2, r3))

        assertEquals(2, result[0].id) // Jamais faite
        assertEquals(3, result[1].id) // 2022 avant 2023
        assertEquals(1, result[2].id)
    }

    @Test
    fun `le tri d'une liste vide retourne une liste vide`() {
        val result = useCase(emptyList())
        assertEquals(0, result.size)
    }
}
