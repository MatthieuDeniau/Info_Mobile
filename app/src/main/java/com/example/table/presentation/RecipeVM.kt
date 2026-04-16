package com.example.table.presentation

import com.example.table.data.local.IngredientDao
import com.example.table.domain.model.RecipeEntity
import com.example.table.domain.model.IngredientEntity

data class RecipeVM(
    val id: Int = -1,
    val name: String = "",
    val ingredients: List<IngredientVM> = emptyList(),
    val instructions: String = "",
    val lastMade: String? = null
) {
    companion object {
        suspend fun fromEntity(
            entity: RecipeEntity,
            ingredientDao: IngredientDao
        ): RecipeVM {
            val ingredientVMs = entity.ingredients.mapNotNull { ingredientId ->
                val ingredient = ingredientDao.getIngredientById(ingredientId)
                ingredient?.let { IngredientVM.fromEntity(it) }
            }

            return RecipeVM(
                id = entity.id,
                name = entity.name,
                ingredients = ingredientVMs,
                instructions = entity.instructions,
                lastMade = entity.lastMade
            )
        }
    }
}

suspend fun RecipeVM.toEntity(): RecipeEntity {
    val id = if (this.id == -1) 0 else this.id

    val ingredientIds = this.ingredients.map { it.id }

    return RecipeEntity(
        id = id,
        name = this.name,
        ingredients = ingredientIds,
        instructions = this.instructions,
        lastMade = this.lastMade
    )
}
