package com.example.table.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.presentation.MealVM

@Composable
fun MealCardTest (meal: MealVM){
    Column (modifier = Modifier
        //.border(width = 1.dp, color = Color.Red)
        .fillMaxSize()
        .background(
            Color(red = 156f / 255f, blue = 44f / 255f, green = 98f / 255f),
            shape = RoundedCornerShape(10.dp)
        )
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

@Composable
fun MealCard (meal: MealVM, onDeleteClick: (MealVM) -> Unit) {
    Box (
        modifier = Modifier
            .background(
                Color(red = 156, blue = 44, green = 98),
                shape = RoundedCornerShape(40.dp)
            )
            .padding(16.dp)
    ) {
        Column (
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    meal.name,
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = Color.Black
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = meal.nextMade,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Column (
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = {onDeleteClick(meal)},
                //modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = {},
                //modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black
                )
            }
        }
    }
}