package com.example.table.presentation.recipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.table.presentation.IngredientVM

@Composable
fun IngredientListEditor(
    ingredients: List<IngredientVM>,
    onIngredientsChange: (List<IngredientVM>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Ingrédients",
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ingredients.forEachIndexed { index, ingredient ->
            IngredientItemField(
                ingredient = ingredient,
                onIngredientChange = { updatedIngredient ->
                    val newList = ingredients.toMutableList()
                    newList[index] = updatedIngredient
                    onIngredientsChange(newList)
                },
                onDelete = {
                    val newList = ingredients.filterIndexed { i, _ -> i != index }
                    onIngredientsChange(newList)
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onIngredientsChange(ingredients + IngredientVM(name = "", quantity = null))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ajouter un ingrédient")
        }
    }
}