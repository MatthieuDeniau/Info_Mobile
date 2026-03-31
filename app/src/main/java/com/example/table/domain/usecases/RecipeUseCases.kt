package com.example.table.domain.usecases

import javax.inject.Inject

data class RecipeUseCases @Inject constructor(
    val getRecipeById: GetRecipeByIdUseCase,
    val getAllRecipes: GetAllRecipesUseCase
)
