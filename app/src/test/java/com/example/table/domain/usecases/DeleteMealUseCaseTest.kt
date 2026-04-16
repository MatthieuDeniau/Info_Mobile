package com.example.table.domain.usecases

import com.example.table.domain.model.MealEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.fakes.FakeDatabase
import com.example.table.presentation.MealVM
import com.example.table.presentation.RecipeVM
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class DeleteMealUseCaseTest {
    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: DeleteMealUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = DeleteMealUseCase(fakeDatabase)
    }

    @Test
    fun `supprimer un repas doit le retirer de la base de donnees`() = runBlocking {
        val date = LocalDate.now()
        val recipe = RecipeVM(id = 1, name = "Pasta")
        val mealVM = MealVM(id = 1, recipe = recipe, date = date, slot = 1)
        
        fakeDatabase.insert(MealEntity(id = 1, recipeId = 1, date = date, slot = 1))
        assertEquals(1, fakeDatabase.meals.value.size)

        useCase(mealVM)

        val meals = fakeDatabase.meals.first()
        assertEquals(0, meals.size)
    }

    @Test
    fun `supprimer un repas inexistant ne doit rien changer`() = runBlocking {
        val date = LocalDate.now()
        val recipe = RecipeVM(id = 1, name = "Pasta")
        val mealVM = MealVM(id = 2, recipe = recipe, date = date, slot = 1)
        
        fakeDatabase.insert(MealEntity(id = 1, recipeId = 1, date = date, slot = 1))

        useCase(mealVM)

        assertEquals(1, fakeDatabase.meals.value.size)
    }
}
