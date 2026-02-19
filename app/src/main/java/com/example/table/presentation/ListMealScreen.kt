package com.example.table.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.presentation.components.MealCard

@Composable
fun ListMealScreen(innerPadding: PaddingValues) {
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ){
        Box {
            Text(
                text = "Repas planifiés",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(red = 156, blue = 44, green = 98),
                    )
                    .padding(vertical = 20.dp),
                style = TextStyle(
                    fontSize = 36.sp,
                    textAlign = TextAlign.Left
                )
            )
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }
        }
        LazyColumn (
            modifier = Modifier
                .background(
                    Color(red = 250, blue = 112, green = 165),
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            meals.forEach { meal ->
                item {
                    MealCard(meal)
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }
}