package com.example.table.data.repository

import com.example.table.domain.usecases.DeleteMealUseCase
import com.example.table.domain.usecases.FormatPeriodLabelUseCase
import com.example.table.domain.usecases.GetAllRecipesUseCase
import com.example.table.domain.usecases.GetLastMealDateUseCase
import com.example.table.domain.usecases.GetMealsForSlotUseCase
import com.example.table.domain.usecases.GetMealsForWeekUseCase
import com.example.table.domain.usecases.GetNextPeriodUseCase
import com.example.table.domain.usecases.GetPreviousPeriodUseCase
import com.example.table.domain.usecases.GetSlotLabelUseCase
import com.example.table.domain.usecases.SaveMealUseCase
import com.example.table.domain.usecases.SortRecipesByLastUsedUseCase
import javax.inject.Inject

data class PlanningRepository @Inject constructor(
    val getMealsForWeek: GetMealsForWeekUseCase,
    val formatPeriodLabel: FormatPeriodLabelUseCase,
    val deleteMeal: DeleteMealUseCase,
    val saveMeal: SaveMealUseCase,
    val getAllRecipes: GetAllRecipesUseCase,
    val getMealsForSlot: GetMealsForSlotUseCase,
    val sortRecipesByLastUsed: SortRecipesByLastUsedUseCase,
    val getLastMealDate: GetLastMealDateUseCase,
    val getNextPeriod: GetNextPeriodUseCase,
    val getPreviousPeriod: GetPreviousPeriodUseCase,
    val getSlotLabel: GetSlotLabelUseCase
)
