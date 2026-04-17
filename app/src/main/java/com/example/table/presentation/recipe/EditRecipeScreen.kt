package com.example.table.presentation.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.presentation.generalComponents.TopBar
import com.example.table.presentation.recipe.components.*

@Composable
fun EditRecipeScreen(
    navController: NavController,
    viewModel: EditRecipeViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    val ingredients by viewModel.ingredients.collectAsState()
    val instructions by viewModel.instructions.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                label = "Modifier la Recette",
            )
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { padding ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(16.dp)
        ){

            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    RecipeNameField(
                        name = name,
                        onNameChange = { value -> viewModel.onNameChange(value) }
                    )
                }
                item {
                    IngredientListEditor(
                        ingredients = ingredients,
                        onIngredientsChange = { value -> viewModel.onIngredientsChange(value) }
                    )
                }
                item {
                    InstructionsField(
                        instructions = instructions,
                        onInstructionsChange = { value -> viewModel.onInstructionsChange(value) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    RecipeActionButtons(
                        onSave = {
                            viewModel.updateRecipe {
                                navController.popBackStack()
                            } },
                        onCancel = { navController.popBackStack() },
                        saveButtonText = "Mettre à jour"
                    )
                }
            }
        }
    }
}