package com.example.table.di

import android.content.Context
import androidx.room.Room
import com.example.table.data.local.AppDatabase
import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
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
    fun provideSettingsDao(db: AppDatabase) = db.settingsDao

    @Provides
    fun provideThemeDao(db: AppDatabase) = db.themeDao
}
