package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeActionButtons(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    saveButtonText: String = "Enregistrer",
) {
    val colorScheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.primary
            )
        ) {
            Text("Annuler")
        }
        
        Button(
            onClick = onSave,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.primary
            )
        ) {
            Text(saveButtonText)
        }
    }
}