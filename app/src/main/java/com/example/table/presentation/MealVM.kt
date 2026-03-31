package com.example.table.presentation

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.MealEntity
import java.time.LocalDate

data class MealVM(
    val id: Int = -1,
    val recipe: RecipeVM,
    val date: LocalDate,
    val slot: Int
) {
    companion object {
        suspend fun fromEntity(
            entity: MealEntity,
            recipeDao: RecipeDao,
            ingredientDao: IngredientDao
        ): MealVM {

            val recipeEntity = recipeDao.getRecipeByIdOnce(entity.recipeId)
                ?: error("Recette introuvable pour id=${entity.recipeId}")

            val recipeVM = RecipeVM.fromEntity(recipeEntity, ingredientDao)

            return MealVM(
                id = entity.id,
                recipe = recipeVM,
                date = entity.date,
                slot = entity.slot
            )
        }
    }
}

fun MealVM.toEntity(): MealEntity {
    val id = if (this.id == -1) 0 else this.id

    return MealEntity(
        id = id,
        recipeId = this.recipe.id,
        date = this.date,
        slot = this.slot
    )
}