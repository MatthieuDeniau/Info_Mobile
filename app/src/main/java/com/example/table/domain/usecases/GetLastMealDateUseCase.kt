package com.example.table.domain.usecases

import com.example.table.data.local.MealDao
import java.time.LocalDate
import javax.inject.Inject

class GetLastMealDateUseCase @Inject constructor(
    private val mealDao: MealDao
) {
    suspend operator fun invoke(): LocalDate? {
        return mealDao.getLastMealDate()
    }
}