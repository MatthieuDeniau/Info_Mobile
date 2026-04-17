package com.example.table.presentation.planning.components

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
import androidx.compose.material3.MaterialTheme
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
import com.example.table.presentation.RecipeVM
import java.time.format.DateTimeFormatter

@Composable
fun MealCard (meal: MealVM, recipe: RecipeVM, onDeleteClick: (MealVM) -> Unit) {
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
                    recipe.name,
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.tertiary
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
                    text = meal.date.format(DateTimeFormatter.ofPattern("MMMM")),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Column (
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = { onDeleteClick(meal) },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
