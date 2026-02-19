package com.example.table.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.presentation.MealVM

@Composable
fun MealCard (meal: MealVM){
    Column (modifier = Modifier
        .border(width = 1.dp, color = Color.Red)
        .fillMaxSize()
    ) {
        Text(meal.name,
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(meal.description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        for ((index, ingredient) in meal.ingredients.withIndex()) {
            Text("${index + 1}. $ingredient",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(meal.lastMade ?: "Jamais fait",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}