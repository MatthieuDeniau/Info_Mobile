package com.example.table.data.repository

import com.example.table.domain.usecases.DeleteRecipeUseCase
import com.example.table.domain.usecases.GetAllRecipesUseCase
import com.example.table.domain.usecases.GetRecipeByIdUseCase
import com.example.table.domain.usecases.SaveRecipeUseCase
import com.example.table.domain.usecases.SearchRecipesByNameUseCase
import com.example.table.domain.usecases.SortRecipesByLastUsedUseCase
import com.example.table.domain.usecases.UpdateRecipeUseCase
import javax.inject.Inject

data class RecipeRepository @Inject constructor(
    val getRecipeById: GetRecipeByIdUseCase,
    val getAllRecipes: GetAllRecipesUseCase,
    val sortRecipesByLastUsed: SortRecipesByLastUsedUseCase,
    val saveRecipe: SaveRecipeUseCase,
    val deleteRecipe: DeleteRecipeUseCase,
    val updateRecipe: UpdateRecipeUseCase,
    val searchRecipesByName: SearchRecipesByNameUseCase
)