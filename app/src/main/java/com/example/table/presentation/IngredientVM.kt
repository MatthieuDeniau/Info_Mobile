package com.example.table.presentation

import com.example.table.domain.model.IngredientEntity

data class IngredientVM(
    val id: Int = -1,
    val name: String = ""
) {
    companion object {
        fun fromEntity(entity: IngredientEntity): IngredientVM {
            return IngredientVM(
                id = entity.id,
                name = entity.name
            )
        }
    }
}

fun IngredientVM.toEntity(): IngredientEntity {
    val id = if (this.id == -1) 0 else this.id
    return IngredientEntity(
        id = id,
        name = this.name,
    )
}
