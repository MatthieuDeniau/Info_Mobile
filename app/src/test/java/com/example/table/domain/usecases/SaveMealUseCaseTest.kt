package com.example.table.domain.usecases

import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class SaveMealUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: SaveMealUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = SaveMealUseCase(fakeDatabase, fakeDatabase)
    }

    @Test
    fun `sauvegarder un repas doit l'ajouter a la base de donnees et mettre a jour la date de la recette`() = runBlocking {
        // Arrange
        val recipeId = 1
        val date = LocalDate.of(2024, 10, 10)
        val slot = 1
        fakeDatabase.insertRecipe(RecipeEntity(id = recipeId, name = "Pasta", ingredients = emptyList(), instructions = "", lastMade = null))

        // Act
        useCase(recipeId, date, slot)

        // Assert
        val meals = fakeDatabase.meals.first()
        assertEquals(1, meals.size)
        assertEquals(recipeId, meals[0].recipeId)
        
        val updatedRecipe = fakeDatabase.getRecipeByIdOnce(recipeId)
        assertEquals(date.toString(), updatedRecipe?.lastMade)
    }
}
