package com.example.table.presentation.planning.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.table.blanc
import com.example.table.gris
import com.example.table.noir
import com.example.table.presentation.planning.DayMeals
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DayCard(
    dayMeals: DayMeals,
    onAddMealClick: (LocalDate) -> Unit,
    onSlotClick: (Int, LocalDate) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = blanc),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH)
                val formattedDate = dayMeals.date.format(formatter)
                    .replaceFirstChar { it.uppercase() }

                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.titleLarge,
                    color = noir,
                    modifier = Modifier.weight(1f)
                )

                FilledIconButton(
                    onClick = { onAddMealClick(dayMeals.date) },
                    shape = RoundedCornerShape(12.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = gris,
                        contentColor = noir
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Ajouter un repas",
                        tint = noir
                    )
                }
            }

            if (dayMeals.mealsBySlot[1] != null) {
                MealSlot(
                    label = "Petit-déjeuner",
                    meals = dayMeals.mealsBySlot[1] ?: emptyList(),
                    date = dayMeals.date,
                    slot = 1,
                    onSlotClick = onSlotClick
                )
            }

            if (dayMeals.mealsBySlot[2] != null)  {
                MealSlot(
                    label = "Déjeuner",
                    meals = dayMeals.mealsBySlot[2] ?: emptyList(),
                    date = dayMeals.date,
                    slot = 2,
                    onSlotClick = onSlotClick
                )
            }

            if (dayMeals.mealsBySlot[3] != null) {
                MealSlot(
                    label = "En-cas",
                    meals = dayMeals.mealsBySlot[3] ?: emptyList(),
                    date = dayMeals.date,
                    slot = 3,
                    onSlotClick = onSlotClick
                )
            }

            if (dayMeals.mealsBySlot[4] != null) {
                MealSlot(
                    label = "Dîner",
                    meals = dayMeals.mealsBySlot[4] ?: emptyList(),
                    date = dayMeals.date,
                    slot = 4,
                    onSlotClick = onSlotClick
                )
            }

            if (dayMeals.mealsBySlot.values.all { it.isEmpty() }) {
                Text(
                    text = "Aucun repas planifié",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = noir
                )
            }
        }
    }
}
