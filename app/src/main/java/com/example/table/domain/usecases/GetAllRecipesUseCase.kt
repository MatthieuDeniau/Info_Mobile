package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.presentation.RecipeVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    operator fun invoke(): Flow<List<RecipeVM>> {
        return recipeDao.getAllRecipes().map { entities ->
            entities.map { RecipeVM.fromEntity(it, ingredientDao) }
        }
    }
}
