package com.example.table.presentation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.blanc
import com.example.table.gris
import com.example.table.navigation.Screen
import com.example.table.noir
import com.example.table.presentation.RecipeVM
import com.example.table.presentation.components.TopBar
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AddEditPlanningScreen(
    navController: NavController,
    viewModel: AddEditPlanningViewModel = hiltViewModel()
) {
    val recipeList by viewModel.recipeList.collectAsState()
    val selectedRecipe by viewModel.selectedRecipe
    val slot by viewModel.slot
    val selectedDate by viewModel.date

    Scaffold(
        topBar = {
            TopBar(
                label = "Ajouter un repas",
                rightIcon = Icons.Default.Close,
                onRightClick = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditPlanningEvent.SaveMeal)
                    navController.navigate(Screen.PlanningScreen.route) {
                        popUpTo(Screen.PlanningScreen.route) { inclusive = true }
                    }
                },
                containerColor = noir,
                contentColor = blanc,
                shape = RoundedCornerShape(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Enregistrer"
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(gris)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                val formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH)
                val formattedDate = selectedDate.format(formatter)
                    .replaceFirstChar { it.uppercase() }

                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 24.sp
                    ),
                    color = noir,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val slots = listOf(
                        1 to "Matin",
                        2 to "Midi",
                        3 to "En-cas",
                        4 to "Soir"
                    )

                    slots.forEach { (id, label) ->
                        Button(
                            onClick = { viewModel.onEvent(AddEditPlanningEvent.SelectedSlot(id)) },
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (slot == id) noir else blanc,
                                contentColor = if (slot == id) blanc else noir
                            ),
                            border = if (slot != id) BorderStroke(1.dp, Color.Black.copy(alpha = 0.25f)) else null,
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Text(
                                text = label,
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Choisir une recette",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp
                    ),
                    color = noir,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recipeList) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            isSelected = selectedRecipe?.id == recipe.id,
                            onSelect = { viewModel.onEvent(AddEditPlanningEvent.SelectedRecipe(recipe)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeItem(
    recipe: RecipeVM,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) noir else blanc,
            contentColor = if (isSelected) blanc else noir
        ),
        border = if (!isSelected) BorderStroke(1.dp, Color.Black.copy(alpha = 0.25f)) else null
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Sélectionné",
                    tint = blanc
                )
            }
        }
    }
}