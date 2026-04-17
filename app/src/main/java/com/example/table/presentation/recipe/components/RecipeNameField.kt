package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
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
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.tertiary,
            unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedLabelColor = MaterialTheme.colorScheme.tertiary,
            unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.tertiary
        )
    )
}
