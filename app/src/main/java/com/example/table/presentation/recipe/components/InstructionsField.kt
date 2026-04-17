package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionsField(
    instructions: String,
    onInstructionsChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tertiaryColor = MaterialTheme.colorScheme.tertiary

    OutlinedTextField(
        value = instructions,
        onValueChange = onInstructionsChange,
        label = {
            Text("Instructions de préparation")
                },
        placeholder = {
            Text("Détaillez ici les étapes de votre recette...")
                      },
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        singleLine = false,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = tertiaryColor,
            unfocusedTextColor = tertiaryColor,
            focusedBorderColor = tertiaryColor,
            unfocusedBorderColor = tertiaryColor,
            focusedLabelColor = tertiaryColor,
            unfocusedLabelColor = tertiaryColor,
            cursorColor = tertiaryColor
        )
    )
}
