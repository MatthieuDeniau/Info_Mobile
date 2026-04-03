package com.example.table.domain.usecases

import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetRecipeByIdUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetRecipeByIdUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetRecipeByIdUseCase(fakeDatabase, fakeDatabase)
    }

    @Test
    fun `recuperer une recette existante doit retourner le bon VM`() = runBlocking {
        // Arrange
        val recipe = RecipeEntity(1, "Omelette", emptyList(), "Cook eggs", null)
        fakeDatabase.insertRecipe(recipe)

        // Act
        val result = useCase(1)

        // Assert
        assertEquals("Omelette", result?.name)
        assertEquals(1, result?.id)
    }

    @Test
    fun `recuperer une recette inexistante doit retourner null`() = runBlocking {
        // Arrange
        fakeDatabase.deleteAll()

        // Act
        val result = useCase(99)

        // Assert
        assertNull(result)
    }

    @Test
    fun `recuperer une recette existante parmi d'autres doit retourner la bonne`() = runBlocking {
        // Arrange
        fakeDatabase.insertRecipe(RecipeEntity(1, "Pasta", emptyList(), "Instructions", null))
        fakeDatabase.insertRecipe(RecipeEntity(2, "Pizza", emptyList(), "Instructions", null))

        // Act
        val result = useCase(2)

        // Assert
        assertEquals("Pizza", result?.name)
        assertEquals(2, result?.id)
    }
}
