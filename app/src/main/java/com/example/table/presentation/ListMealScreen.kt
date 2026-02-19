package com.example.table.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.table.presentation.components.MealCard

@Composable
fun ListMealScreen(innerPadding: PaddingValues) {
    LazyColumn(modifier = Modifier
        .padding(innerPadding)
        .border(width = 1.dp, color = Color.Blue)
        .fillMaxSize()
    ) {
        meals.forEach { meal ->
            item {
                MealCard(meal)
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}