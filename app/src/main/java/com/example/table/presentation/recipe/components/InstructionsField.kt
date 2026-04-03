package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstructionsField(
    instructions: String,
    onInstructionsChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
        singleLine = false
    )
}
