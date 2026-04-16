package com.example.table.domain.usecases

import com.example.table.domain.model.RecipeEntity
import com.example.table.domain.model.MealEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetMealsForWeekUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetMealsForWeekUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetMealsForWeekUseCase(fakeDatabase, fakeDatabase, fakeDatabase)
    }

    @Test
    fun `doit retourner les repas de la semaine`() = runBlocking {
        val monday = LocalDate.of(2024, 10, 7) // Un lundi
        val tuesday = monday.plusDays(1)
        val nextMonday = monday.plusWeeks(1)

        val recipe = RecipeEntity(id = 1, name = "Pasta", ingredients = emptyList(), instructions = "", lastMade = null)
        fakeDatabase.insertRecipe(recipe)

        fakeDatabase.insert(MealEntity(recipeId = 1, date = monday, slot = 1))
        fakeDatabase.insert(MealEntity(recipeId = 1, date = tuesday, slot = 2))
        fakeDatabase.insert(MealEntity(recipeId = 1, date = nextMonday, slot = 1)) // Hors semaine

        val result = useCase(monday).first()

        assertEquals(7, result.size) // 7 jours dans la semaine
        assertEquals(1, result.find { it.date == monday }?.mealsBySlot?.size)
        assertEquals(1, result.find { it.date == tuesday }?.mealsBySlot?.size)
        assertEquals(0, result.find { it.date == nextMonday }?.mealsBySlot ?: 0) // Pas dans ce résultat (indexé par date de début)
        
        val mondayMeals = result.find { it.date == monday }?.mealsBySlot?.get(1)
        assertEquals(1, mondayMeals?.size)
        assertEquals("Pasta", mondayMeals?.get(0)?.recipe?.name)
    }
}
