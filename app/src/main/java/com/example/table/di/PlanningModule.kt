package com.example.table.di

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.data.repository.PlanningRepository
import com.example.table.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanningModule {

    @Provides
    @Singleton
    fun providePlanningUseCases(
        recipeDao: RecipeDao,
        ingredientDao: IngredientDao,
        mealDao: MealDao
    ): PlanningRepository {
        return PlanningRepository(
            getMealsForWeek = GetMealsForWeekUseCase(mealDao, recipeDao, ingredientDao),
            formatPeriodLabel = FormatPeriodLabelUseCase(),
            deleteMeal = DeleteMealUseCase(mealDao),
            saveMeal = SaveMealUseCase(mealDao, recipeDao),
            getAllRecipes = GetAllRecipesUseCase(recipeDao, ingredientDao),
            getMealsForSlot = GetMealsForSlotUseCase(mealDao, recipeDao, ingredientDao),
            sortRecipesByLastUsed = SortRecipesByLastUsedUseCase(),
            getLastMealDate = GetLastMealDateUseCase(mealDao),
            getNextPeriod = GetNextPeriodUseCase(),
            getPreviousPeriod = GetPreviousPeriodUseCase(),
            getSlotLabel = GetSlotLabelUseCase()
        )
    }
}
