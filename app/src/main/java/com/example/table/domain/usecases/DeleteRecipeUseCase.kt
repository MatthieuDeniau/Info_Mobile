package com.example.table.domain.usecases

import com.example.table.data.local.RecipeDao
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
    private val dao: RecipeDao
) {
    suspend operator fun invoke(id: Int) {
        val entity = dao.getRecipeByIdOnce(id)
        if (entity != null) {
            dao.delete(entity)
        }
    }
}
