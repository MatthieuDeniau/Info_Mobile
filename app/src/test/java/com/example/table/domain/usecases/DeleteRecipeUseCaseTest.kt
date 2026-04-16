package com.example.table.domain.usecases

import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DeleteRecipeUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: DeleteRecipeUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = DeleteRecipeUseCase(fakeDatabase)
    }

    @Test
    fun `supprimer une recette existante doit la retirer de la base`() = runBlocking {
        val recipe = RecipeEntity(id = 1, name = "Pizza", ingredients = emptyList(), instructions = "", lastMade = null)
        fakeDatabase.insertRecipe(recipe)
        assertEquals(1, fakeDatabase.recipes.value.size)

        useCase(1)

        assertEquals(0, fakeDatabase.recipes.value.size)
    }

    @Test
    fun `supprimer une recette inexistante ne doit rien changer`() = runBlocking {
        val recipe = RecipeEntity(id = 1, name = "Pizza", ingredients = emptyList(), instructions = "", lastMade = null)
        fakeDatabase.insertRecipe(recipe)

        useCase(2)

        assertEquals(1, fakeDatabase.recipes.value.size)
    }
}
