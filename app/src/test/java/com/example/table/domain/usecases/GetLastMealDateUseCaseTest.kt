package com.example.table.domain.usecases

import com.example.table.domain.model.MealEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class GetLastMealDateUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: GetLastMealDateUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = GetLastMealDateUseCase(fakeDatabase)
    }

    @Test
    fun `recuperer la date du dernier repas doit retourner la date la plus recente`() = runBlocking {
        fakeDatabase.insert(MealEntity(id = 1, recipeId = 1, date = LocalDate.of(2024, 1, 1), slot = 1))
        fakeDatabase.insert(MealEntity(id = 2, recipeId = 2, date = LocalDate.of(2024, 1, 10), slot = 1))
        fakeDatabase.insert(MealEntity(id = 3, recipeId = 1, date = LocalDate.of(2024, 1, 5), slot = 2))

        val result = useCase()

        assertEquals(LocalDate.of(2024, 1, 10), result)
    }

    @Test
    fun `recuperer la date du dernier repas quand il n'y en a pas doit retourner null`() = runBlocking {
        val result = useCase()
        assertEquals(null, result)
    }
}
