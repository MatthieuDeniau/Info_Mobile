package com.example.table.domain.usecases

import com.example.table.fakes.FakeDatabase
import com.example.table.presentation.IngredientVM
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SaveRecipeUseCaseTest {

    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: SaveRecipeUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = SaveRecipeUseCase(fakeDatabase, fakeDatabase)
    }

    @Test
    fun `sauvegarder une recette doit inserer la recette et ses ingredients`() = runBlocking {
        val ingredients = listOf(
            IngredientVM(name = "Tomate", quantity = 2.0, unit = "unités"),
            IngredientVM(name = "Pâtes", quantity = 500.0, unit = "g")
        )

        useCase("Pasta", ingredients, "Cook well")

        val recipes = fakeDatabase.recipes.value
        assertEquals(1, recipes.size)
        assertEquals("Pasta", recipes[0].name)
        assertEquals(2, recipes[0].ingredients.size)
        
        val savedIngredients = fakeDatabase.ingredients.value
        assertEquals(2, savedIngredients.size)
    }

    @Test
    fun `sauvegarder une recette sans nom ne doit rien faire`() = runBlocking {
        useCase("", emptyList(), "")
        assertEquals(0, fakeDatabase.recipes.value.size)
    }
}
