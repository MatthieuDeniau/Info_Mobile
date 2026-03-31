package com.example.table.domain.usecases

import com.example.table.data.local.MealDao
import com.example.table.presentation.MealVM
import com.example.table.presentation.toEntity
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(
    private val mealDao: MealDao
) {
    suspend operator fun invoke(meal: MealVM) {
        mealDao.delete(meal.toEntity())
    }
}
