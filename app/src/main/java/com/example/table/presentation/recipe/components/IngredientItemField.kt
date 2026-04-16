package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.table.noir
import com.example.table.presentation.IngredientVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientItemField(
    ingredient: IngredientVM,
    onIngredientChange: (IngredientVM) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = ingredient.name,
                onValueChange = { onIngredientChange(ingredient.copy(name = it)) },
                label = { Text("Ingrédient") },
                modifier = Modifier.weight(1.1f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = noir,
                    unfocusedTextColor = noir,
                    focusedBorderColor = noir,
                    unfocusedBorderColor = noir,
                    focusedLabelColor = noir,
                    unfocusedLabelColor = noir,
                    cursorColor = noir
                )
            )

            OutlinedTextField(
                value = ingredient.quantity?.toString() ?: "",
                onValueChange = {
                    val newQuantity = it.toDoubleOrNull()
                    onIngredientChange(ingredient.copy(quantity = newQuantity))
                },
                label = { Text("Quantité") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = noir,
                    unfocusedTextColor = noir,
                    focusedBorderColor = noir,
                    unfocusedBorderColor = noir,
                    focusedLabelColor = noir,
                    unfocusedLabelColor = noir,
                    cursorColor = noir
                )
            )

            OutlinedTextField(
                value = ingredient.unit ?: "",
                onValueChange = { onIngredientChange(ingredient.copy(unit = it)) },
                label = { Text("Unité") },
                modifier = Modifier.weight(0.9f),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = noir,
                    unfocusedTextColor = noir,
                    focusedBorderColor = noir,
                    unfocusedBorderColor = noir,
                    focusedLabelColor = noir,
                    unfocusedLabelColor = noir,
                    cursorColor = noir
                )
            )

            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Supprimer l'ingrédient",
                    tint = noir
                )
            }
        }
    }
}
