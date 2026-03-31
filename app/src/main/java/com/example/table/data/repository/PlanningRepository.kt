package com.example.table.data.repository

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.MealEntity
import com.example.table.presentation.MealVM
import com.example.table.presentation.RecipeVM
import com.example.table.presentation.list.DayMeals
import com.example.table.presentation.toEntity
import com.example.table.utils.Week
import com.example.table.utils.days
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanningRepository @Inject constructor(
    private val recipeDao: RecipeDao,
    private val mealDao: MealDao,
    private val ingredientDao: IngredientDao
) {
    fun getMealsForPeriod(startDate: LocalDate): Flow<List<DayMeals>> {
        val week = Week(startDate, startDate.plusDays(6))
        return mealDao.getAllMeals().map { entities ->
            convertToDayMeals(entities, startDate)
        }
    }

    fun formatPeriodLabel(startDate: LocalDate): String {
        val endDate = startDate.plusDays(6)
        fun Int.twoDigits(): String = this.toString().padStart(2, '0')
        fun LocalDate.monthLabel(): String =
            this.month.getDisplayName(TextStyle.FULL, Locale.FRENCH)

        return "${startDate.dayOfMonth.twoDigits()} - ${endDate.dayOfMonth.twoDigits()} ${endDate.monthLabel()}"
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

    suspend fun deleteMeal(meal: MealVM) {
        mealDao.delete(meal.toEntity())
    }

    suspend fun saveMeal(recipeId: Int, date: LocalDate, slot: Int) {
        val mealEntity = MealEntity(
            recipeId = recipeId,
            date = date,
            slot = slot
        )
        mealDao.insert(mealEntity)
    }

    fun getAllRecipes(): Flow<List<RecipeVM>> {
        return recipeDao.getAllRecipes().map { entities ->
            entities.map { RecipeVM.fromEntity(it, ingredientDao) }
        }
    }
}
