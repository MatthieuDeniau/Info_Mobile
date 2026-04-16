package com.example.table.domain.usecases

import com.example.table.domain.model.MealEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetMealsForSlotUseCaseTest {

    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetMealsForSlotUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetMealsForSlotUseCase(fakeDatabase, fakeDatabase, fakeDatabase)
    }

    @Test
    fun `recuperer les repas pour un creneau doit filtrer par date et slot`() = runBlocking {
        val date1 = LocalDate.of(2024, 10, 21)
        val date2 = LocalDate.of(2024, 10, 22)
        
        fakeDatabase.insertRecipe(RecipeEntity(id = 1, name = "Recipe 1", ingredients = emptyList(), instructions = "", lastMade = null))
        
        fakeDatabase.insert(MealEntity(id = 1, recipeId = 1, date = date1, slot = 1))
        fakeDatabase.insert(MealEntity(id = 2, recipeId = 1, date = date1, slot = 2))
        fakeDatabase.insert(MealEntity(id = 3, recipeId = 1, date = date2, slot = 1))

        val result = useCase(date1, 1).first()

        assertEquals(1, result.size)
        assertEquals(1, result[0].id)
    }
}
