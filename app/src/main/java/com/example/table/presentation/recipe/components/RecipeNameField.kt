package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecipeNameField(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Nom de la recette") },
        placeholder = { Text("Ex: Lasagnes à la bolognaise") },
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
}
