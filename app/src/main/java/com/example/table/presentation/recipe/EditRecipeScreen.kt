package com.example.table.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.gris
import com.example.table.presentation.planning.components.TopBar
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
                rightIcon = Icons.Default.Close,
                onRightClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gris)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                item {
                    RecipeNameField(
                        name = name,
                        onNameChange = viewModel::onNameChange
                    )
                }

                item {
                    IngredientListEditor(
                        ingredients = ingredients,
                        onIngredientsChange = viewModel::onIngredientsChange
                    )
                }

                item {
                    InstructionsField(
                        instructions = instructions,
                        onInstructionsChange = viewModel::onInstructionsChange
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    RecipeActionButtons(
                        onSave = {
                            viewModel.updateRecipe {
                                navController.popBackStack()
                            }
                        },
                        onCancel = { navController.popBackStack() },
                        saveButtonText = "Mettre à jour"
                    )
                }

                item { Spacer(modifier = Modifier.height(32.dp)) }
            }

        }
    }
}
