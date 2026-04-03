package com.example.table.domain.usecases

import com.example.table.domain.model.IngredientEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllRecipesUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetAllRecipesUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetAllRecipesUseCase(fakeDatabase, fakeDatabase)
    }

    @Test
    fun `recuperer toutes les recettes doit retourner la liste complete`() = runBlocking {
        // Arrange
        val ingredient = IngredientEntity(id = 1, name = "Tomate")
        fakeDatabase.insert(ingredient)
        
        val recipe1 = RecipeEntity(1, "A-Recipe", listOf(1), "Instructions 1", null)
        val recipe2 = RecipeEntity(2, "B-Recipe", emptyList(), "Instructions 2", null)
        fakeDatabase.insertRecipe(recipe1)
        fakeDatabase.insertRecipe(recipe2)

        // Act
        val result = useCase().first()

        // Assert
        assertEquals(2, result.size)
        assertEquals("A-Recipe", result[0].name)
        assertEquals("B-Recipe", result[1].name)
    }

    @Test
    fun `recuperer toutes les recettes quand la base est vide doit retourner une liste vide`() = runBlocking {
        // Act
        val result = useCase().first()

        // Assert
        assertEquals(0, result.size)
    }
}
