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

class GetMealsForWeekUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetMealsForWeekUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetMealsForWeekUseCase(fakeDatabase, fakeDatabase, fakeDatabase)
    }

    @Test
    fun `recuperer les repas de la semaine doit retourner 7 jours`() = runBlocking {
        // Arrange
        val startDate = LocalDate.of(2024, 5, 20) // Un lundi

        // Act
        val result = useCase(startDate).first()

        // Assert
        assertEquals(7, result.size)
    }

    @Test
    fun `recuperer les repas de la semaine doit filtrer les repas hors de la plage`() = runBlocking {
        // Arrange
        val startDate = LocalDate.of(2024, 5, 20)
        val recipe = RecipeEntity(1, "Pasta", emptyList(), "Instructions", null)
        fakeDatabase.insertRecipe(recipe)
        
        fakeDatabase.insert(MealEntity(1, 1, startDate, 1)) // Dans la semaine
        fakeDatabase.insert(MealEntity(2, 1, startDate.minusDays(1), 1)) // Avant
        fakeDatabase.insert(MealEntity(3, 1, startDate.plusDays(7), 1)) // Après

        // Act
        val result = useCase(startDate).first()

        // Assert
        val totalMeals = result.sumOf { it.mealsBySlot.values.sumOf { list -> list.size } }
        assertEquals(1, totalMeals)
    }

    @Test
    fun `recuperer les repas de la semaine doit gerer les recettes manquantes`() = runBlocking {
        // Arrange
        val startDate = LocalDate.of(2024, 5, 20)
        // Pas de recette insérée
        fakeDatabase.insert(MealEntity(1, 1, startDate, 1))

        // Act
        val result = useCase(startDate).first()

        // Assert
        val totalMeals = result.sumOf { it.mealsBySlot.values.sumOf { list -> list.size } }
        assertEquals(0, totalMeals) // La recette n'existe pas, donc le repas est ignoré
    }
}
