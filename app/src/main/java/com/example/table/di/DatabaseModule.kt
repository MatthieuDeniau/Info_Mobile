package com.example.table.di

import android.content.Context
import androidx.room.Room
import com.example.table.data.local.AppDatabase
import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideRecipeDao(db: AppDatabase): RecipeDao = db.recipeDao

    @Provides
    fun provideMealDao(db: AppDatabase): MealDao = db.mealDao

    @Provides
    fun provideIngredientDao(db: AppDatabase): IngredientDao = db.ingredientDao

    @Provides
    @Singleton
    fun providePlanningUseCases(
        mealDao: MealDao,
        recipeDao: RecipeDao,
        ingredientDao: IngredientDao
    ): PlanningUseCases {
        return PlanningUseCases(
            getMealsForWeek = GetMealsForWeekUseCase(mealDao, recipeDao, ingredientDao),
            formatPeriodLabel = FormatPeriodLabelUseCase(),
            deleteMeal = DeleteMealUseCase(mealDao),
            saveMeal = SaveMealUseCase(mealDao),
            getAllRecipes = GetAllRecipesUseCase(recipeDao, ingredientDao),
            getMealsForSlot = GetMealsForSlotUseCase(mealDao, recipeDao, ingredientDao)
        )
    }
}
