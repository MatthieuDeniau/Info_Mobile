package com.example.table.domain.usecases

import javax.inject.Inject

data class PlanningUseCases @Inject constructor(
    val getMealsForWeek: GetMealsForWeekUseCase,
    val formatPeriodLabel: FormatPeriodLabelUseCase,
    val deleteMeal: DeleteMealUseCase,
    val saveMeal: SaveMealUseCase,
    val getAllRecipes: GetAllRecipesUseCase,
    val getMealsForSlot: GetMealsForSlotUseCase
)
