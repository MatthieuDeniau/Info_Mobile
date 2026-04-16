package com.example.table.domain.usecases

import javax.inject.Inject

class GetSlotLabelUseCase @Inject constructor() {
    operator fun invoke(slot: Int): String {
        return when (slot) {
            1 -> "Petit-déjeuner"
            2 -> "Déjeuner"
            3 -> "En-cas"
            4 -> "Dîner"
            else -> "Repas"
        }
    }
}
