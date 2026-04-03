package com.example.table.presentation.planning.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.table.gris
import com.example.table.noir
import com.example.table.presentation.MealVM
import java.time.LocalDate

@Composable
fun MealSlot(
    label: String,
    meals: List<MealVM>,
    date: LocalDate,
    slot: Int,
    onSlotClick: (Int, LocalDate) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = gris, shape = RoundedCornerShape(12.dp))
            .clickable { onSlotClick(slot, date) }
            .padding(12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = noir
        )

        Spacer(Modifier.height(4.dp))

        if (meals.isEmpty()) {
            Text(
                text = "Aucun repas",
                style = MaterialTheme.typography.bodySmall,
                color = noir
            )
        } else {
            meals.forEach { meal ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = "● " + meal.recipe.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = noir,
                    )
                }
            }
        }
    }
}
