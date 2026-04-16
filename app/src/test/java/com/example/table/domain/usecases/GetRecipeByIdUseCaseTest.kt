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
    fun `recuperer une recette par son ID doit retourner la recette correspondante`() = runBlocking {
        val recipe = RecipeEntity(id = 1, name = "Pasta", ingredients = emptyList(), instructions = "Cook", lastMade = null)
        fakeDatabase.insertRecipe(recipe)

        val result = useCase(1)

        assertEquals("Pasta", result?.name)
        assertEquals(1, result?.id)
    }

    @Test
    fun `recuperer une recette avec un ID inexistant doit retourner null`() = runBlocking {
        val result = useCase(99)
        assertNull(result)
    }
}
