package com.example.table.domain.usecases

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.IngredientEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.presentation.IngredientVM
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    suspend operator fun invoke(
        name: String,
        ingredients: List<IngredientVM>,
        instructions: String
    ) {
        if (name.isBlank()) return

        val ingredientIds = ingredients.map { ingredientVM ->
            if (ingredientVM.id <= 0) {
                ingredientDao.insert(IngredientEntity(
                    name = ingredientVM.name,
                    quantity = ingredientVM.quantity
                )).toInt()
            } else {
                ingredientDao.getIngredientById(ingredientVM.id)?.let { existing ->
                    if (existing.name != ingredientVM.name || existing.quantity != ingredientVM.quantity) {
                        ingredientDao.insert(IngredientEntity(
                            id = existing.id,
                            name = ingredientVM.name,
                            quantity = ingredientVM.quantity
                        )).toInt()
                    } else {
                        ingredientVM.id
                    }
                } ?: ingredientDao.insert(IngredientEntity(
                    name = ingredientVM.name,
                    quantity = ingredientVM.quantity
                )).toInt()
            }
        }
        
        val recipeEntity = RecipeEntity(
            name = name,
            ingredients = ingredientIds,
            instructions = instructions,
            lastMade = null
        )
        recipeDao.insertRecipe(recipeEntity)
    }
}
