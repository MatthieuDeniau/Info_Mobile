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
    fun `recuperer les repas du creneau doit filtrer par date et slot`() = runBlocking {
        // Arrange
        val targetDate = LocalDate.of(2024, 5, 20)
        val targetSlot = 1
        fakeDatabase.insertRecipe(RecipeEntity(1, "Pizza", emptyList(), "Cook pizza", null))
        
        fakeDatabase.insert(MealEntity(1, 1, targetDate, targetSlot)) // Match
        fakeDatabase.insert(MealEntity(2, 1, targetDate, 2))          // Wrong slot
        fakeDatabase.insert(MealEntity(3, 1, targetDate.plusDays(1), targetSlot)) // Wrong date

        // Act
        val result = useCase(targetDate, targetSlot).first()

        // Assert
        assertEquals(1, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Pizza", result[0].recipe.name)
    }

    @Test
    fun `recuperer les repas du creneau sans match doit retourner une liste vide`() = runBlocking {
        // Arrange
        val targetDate = LocalDate.of(2024, 5, 20)
        fakeDatabase.insertRecipe(RecipeEntity(1, "Pizza", emptyList(), "Cook pizza", null))
        fakeDatabase.insert(MealEntity(1, 1, targetDate, 1))

        // Act
        val result = useCase(targetDate.plusDays(1), 1).first()

        // Assert
        assertEquals(0, result.size)
    }

    @Test
    fun `recuperer les repas du creneau avec recette manquante doit l'ignorer`() = runBlocking {
        // Arrange
        val targetDate = LocalDate.of(2024, 5, 20)
        fakeDatabase.insert(MealEntity(1, 99, targetDate, 1)) // Recette 99 n'existe pas

        // Act
        val result = useCase(targetDate, 1).first()

        // Assert
        assertEquals(0, result.size)
    }
}
