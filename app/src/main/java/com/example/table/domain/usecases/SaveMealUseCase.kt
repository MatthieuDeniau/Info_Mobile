package com.example.table.domain.usecases

import com.example.table.data.local.MealDao
import com.example.table.domain.model.MealEntity
import java.time.LocalDate
import javax.inject.Inject

class SaveMealUseCase @Inject constructor(
    private val mealDao: MealDao
) {
    suspend operator fun invoke(recipeId: Int, date: LocalDate, slot: Int) {
        val mealEntity = MealEntity(
            recipeId = recipeId,
            date = date,
            slot = slot
        )
        mealDao.insert(mealEntity)
    }
}
