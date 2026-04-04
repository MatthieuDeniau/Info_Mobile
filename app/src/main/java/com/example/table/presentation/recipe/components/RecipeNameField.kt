package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.table.noir

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
}
