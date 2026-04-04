package com.example.table.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.RecipeVM
import com.example.table.presentation.planning.components.BottomBar
import com.example.table.presentation.planning.components.TopBar
import com.example.table.blanc
import com.example.table.gris
import com.example.table.noir

@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()

    Scaffold(
        topBar = {
            TopBar(label = "Mes Recettes")
        },
        bottomBar = {
            BottomBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.CreateRecipeScreen.route) },
                containerColor = noir,
                contentColor = blanc,
                shape = RoundedCornerShape(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ajouter une recette")
            }
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .background(gris)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(recipes) { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onEdit = {
                            navController.navigate(Screen.EditRecipeScreen.route + "/${recipe.id}")
                        },
                        onDelete = { viewModel.deleteRecipe(recipe) }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeItem(
    recipe: RecipeVM,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = blanc),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = noir
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (recipe.ingredients.isNotEmpty()) {
                    Text(
                        text = "${recipe.ingredients.size} ingrédients",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(
                onClick = onEdit,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Modifier",
                    tint = noir
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Supprimer",
                    tint = Color.Black
                )
            }
        }
    }
}
