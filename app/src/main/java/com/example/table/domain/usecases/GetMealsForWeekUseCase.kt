package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.MealEntity
import com.example.table.presentation.MealVM
import com.example.table.presentation.RecipeVM
import com.example.table.presentation.planning.DayMeals
import com.example.table.utils.Week
import com.example.table.utils.days
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetMealsForWeekUseCase @Inject constructor(
    private val mealDao: MealDao,
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    operator fun invoke(startDate: LocalDate): Flow<List<DayMeals>> {
        return mealDao.getAllMeals().map { entities ->
            convertToDayMeals(entities, startDate)
        }
    }

    private suspend fun convertToDayMeals(
        entities: List<MealEntity>,
        startDate: LocalDate
    ): List<DayMeals> {
        val week = Week(startDate, startDate.plusDays(6))
        val weekDates = week.days()
        val mealsThisWeek = entities.filter { it.date in weekDates }

        val mealVMs = mealsThisWeek.mapNotNull { entity ->
            convertEntityToVM(entity)
        }

        return weekDates.map { date ->
            val mealsForDay = mealVMs.filter { it.date == date }
            DayMeals(
                date = date,
                mealsBySlot = mealsForDay.groupBy { it.slot }
            )
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
