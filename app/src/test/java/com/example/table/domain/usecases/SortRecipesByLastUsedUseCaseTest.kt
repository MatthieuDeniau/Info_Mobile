package com.example.table.domain.usecases

import com.example.table.presentation.RecipeVM
import org.junit.Assert.assertEquals
import org.junit.Test

class SortRecipesByLastUsedUseCaseTest {

    private val useCase = SortRecipesByLastUsedUseCase()

    @Test
    fun `les recettes jamais faites doivent apparaitre en premier`() {
        val r1 = RecipeVM(id = 1, name = "A", lastMade = "2024-01-01")
        val r2 = RecipeVM(id = 2, name = "B", lastMade = null)
        val r3 = RecipeVM(id = 3, name = "C", lastMade = "")

        val result = useCase(listOf(r1, r2, r3))

        assertEquals(2, result[0].id)
        assertEquals(3, result[1].id)
        assertEquals(1, result[2].id)
    }

    @Test
    fun `les recettes avec date doivent etre triees par date croissante`() {
        val r1 = RecipeVM(id = 1, name = "A", lastMade = "2024-02-01")
        val r2 = RecipeVM(id = 2, name = "B", lastMade = "2024-01-01")
        
        val result = useCase(listOf(r1, r2))

        assertEquals(2, result[0].id)
        assertEquals(1, result[1].id)
    }
}
