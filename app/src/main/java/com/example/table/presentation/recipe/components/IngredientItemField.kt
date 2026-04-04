package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.table.presentation.IngredientVM

@Composable
fun IngredientItemField(
    ingredient: IngredientVM,
    onIngredientChange: (IngredientVM) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = ingredient.name,
            onValueChange = { onIngredientChange(ingredient.copy(name = it)) },
            label = { Text("Ingrédient") },
            modifier = Modifier.weight(2f),
            singleLine = true
        )
        
        OutlinedTextField(
            value = ingredient.quantity,
            onValueChange = { onIngredientChange(ingredient.copy(quantity = it)) },
            label = { Text("Quantité") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Supprimer l'ingrédient",
                tint = Color.Black
            )
        }
    }
}
