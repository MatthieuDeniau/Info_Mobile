package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.MealEntity
import com.example.table.presentation.MealVM
import com.example.table.presentation.RecipeVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetMealsForSlotUseCase @Inject constructor(
    private val mealDao: MealDao,
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    operator fun invoke(date: LocalDate, slot: Int): Flow<List<MealVM>> {
        return mealDao.getAllMeals().map { entities ->
            entities.filter { it.date == date && it.slot == slot }
                .mapNotNull { convertEntityToVM(it) }
        }
    }

    private suspend fun convertEntityToVM(entity: MealEntity): MealVM? {
        val recipeEntity = recipeDao.getRecipeByIdOnce(entity.recipeId)
            ?: return null

        val recipeVM = RecipeVM.fromEntity(recipeEntity, ingredientDao)

        return MealVM(
            id = entity.id,
            recipe = recipeVM,
            date = entity.date,
            slot = entity.slot
        )
    }
}
