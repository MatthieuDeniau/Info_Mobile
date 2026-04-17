package com.example.table.di

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.data.repository.RecipeRepository
import com.example.table.domain.usecases.*
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
    ): RecipeRepository {
        return RecipeRepository(
            getRecipeById = GetRecipeByIdUseCase(recipeDao, ingredientDao),
            getAllRecipes = GetAllRecipesUseCase(recipeDao, ingredientDao),
            sortRecipesByLastUsed = SortRecipesByLastUsedUseCase(),
            saveRecipe = SaveRecipeUseCase(recipeDao, ingredientDao),
            deleteRecipe = DeleteRecipeUseCase(recipeDao),
            updateRecipe = UpdateRecipeUseCase(recipeDao, ingredientDao),
            searchRecipesByName = SearchRecipesByNameUseCase(recipeDao, ingredientDao)
        )
    }
}
