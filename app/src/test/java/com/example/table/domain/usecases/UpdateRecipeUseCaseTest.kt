package com.example.table.domain.usecases

import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import com.example.table.presentation.IngredientVM
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UpdateRecipeUseCaseTest {

    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: UpdateRecipeUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = UpdateRecipeUseCase(fakeDatabase, fakeDatabase)
    }

    @Test
    fun `mettre a jour une recette doit modifier les donnees en base`() = runBlocking {
        val originalRecipe = RecipeEntity(id = 1, name = "Original", ingredients = emptyList(), instructions = "", lastMade = null)
        fakeDatabase.insertRecipe(originalRecipe)

        val newIngredients = listOf(IngredientVM(name = "New Ing", quantity = 1.0, unit = "unit"))
        useCase(1, "Updated Name", newIngredients, "New instructions")

        val updated = fakeDatabase.getRecipeByIdOnce(1)
        assertEquals("Updated Name", updated?.name)
        assertEquals("New instructions", updated?.instructions)
        assertEquals(1, updated?.ingredients?.size)
    }

    @Test
    fun `mettre a jour avec un nom vide ne doit rien faire`() = runBlocking {
        val originalRecipe = RecipeEntity(id = 1, name = "Original", ingredients = emptyList(), instructions = "", lastMade = null)
        fakeDatabase.insertRecipe(originalRecipe)

        useCase(1, "", emptyList(), "")

        val recipe = fakeDatabase.getRecipeByIdOnce(1)
        assertEquals("Original", recipe?.name)
    }
}
