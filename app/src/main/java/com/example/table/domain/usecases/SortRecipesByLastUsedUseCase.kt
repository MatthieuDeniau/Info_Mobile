package com.example.table.domain.usecases

import com.example.table.presentation.RecipeVM
import javax.inject.Inject

class SortRecipesByLastUsedUseCase @Inject constructor() {
    operator fun invoke(list: List<RecipeVM>): List<RecipeVM> {
        val neverMade = list.filter { it.lastMade.isNullOrBlank() }

        val withDate = list.filter { !it.lastMade.isNullOrBlank() }
            .sortedBy { it.lastMade }

        return neverMade + withDate
    }

}
