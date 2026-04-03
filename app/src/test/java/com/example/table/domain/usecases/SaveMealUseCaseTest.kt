package com.example.table.domain.usecases

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
        useCase = SaveMealUseCase(fakeDatabase)
    }

    @Test
    fun `sauvegarder un repas doit l'ajouter a la base de donnees`() = runBlocking {
        // Arrange
        val recipeId = 1
        val date = LocalDate.of(2024, 10, 10)
        val slot = 1

        // Act
        useCase(recipeId, date, slot)

        // Assert
        val meals = fakeDatabase.meals.first()
        assertEquals(1, meals.size)
        assertEquals(recipeId, meals[0].recipeId)
        assertEquals(date, meals[0].date)
        assertEquals(slot, meals[0].slot)
    }

    @Test
    fun `sauvegarder plusieurs repas doit tous les ajouter`() = runBlocking {
        // Arrange
        val date = LocalDate.now()

        // Act
        useCase(1, date, 1)
        useCase(2, date, 2)

        // Assert
        val meals = fakeDatabase.meals.first()
        assertEquals(2, meals.size)
    }
}
