package com.example.table.di

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.usecases.GetAllRecipesUseCase
import com.example.table.domain.usecases.GetRecipeByIdUseCase
import com.example.table.domain.usecases.RecipeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Provides
    @Singleton
    fun provideRecipeUseCases(
        recipeDao: RecipeDao,
        ingredientDao: IngredientDao
    ): RecipeUseCases {
        return RecipeUseCases(
            getRecipeById = GetRecipeByIdUseCase(recipeDao, ingredientDao),
            getAllRecipes = GetAllRecipesUseCase(recipeDao, ingredientDao)
        )
    }
}
