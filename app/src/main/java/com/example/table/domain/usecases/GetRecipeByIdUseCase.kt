package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.presentation.RecipeVM
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    suspend operator fun invoke(id: Int): RecipeVM? {
        val entity = recipeDao.getRecipeByIdOnce(id) ?: return null
        return RecipeVM.fromEntity(entity, ingredientDao)
    }
}
