package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.presentation.RecipeVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRecipesByNameUseCase @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao,
) {
    operator fun invoke(query: String): Flow<List<RecipeVM>> {
        return recipeDao.searchRecipesByName(query).map { entities ->
            entities.map { entity ->
                RecipeVM.fromEntity(entity, ingredientDao)
            }
        }
    }
}