package com.example.table.presentation

import com.example.table.domain.model.IngredientEntity

data class IngredientVM(
    val id: Int = -1,
    val name: String = "",
    val quantity: Double? = null,
    val unit: String? = null
) {
    companion object {
        fun fromEntity(entity: IngredientEntity): IngredientVM {
            return IngredientVM(
                id = entity.id,
                name = entity.name,
                quantity = entity.quantity,
                unit = entity.unit
            )
        }
    }
}

fun IngredientVM.toEntity(): IngredientEntity {
    val id = if (this.id == -1) 0 else this.id
    return IngredientEntity(
        id = id,
        name = this.name,
        quantity = this.quantity,
        unit = this.unit
    )
}

fun IngredientVM.formatQuantity(): String {
    val q = quantity?.let {
        if (it % 1.0 == 0.0) it.toInt().toString() else it.toString()
    }
    return when {
        q != null && !unit.isNullOrBlank() -> "$q $unit"
        q != null -> q
        !unit.isNullOrBlank() -> unit
        else -> ""
    }
}