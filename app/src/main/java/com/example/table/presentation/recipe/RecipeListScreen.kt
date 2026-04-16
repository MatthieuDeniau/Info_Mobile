package com.example.table.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.RecipeVM
import com.example.table.presentation.generalComponents.BottomBar
import com.example.table.presentation.generalComponents.TopBar
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
            TopBar(
                label = "Mes Recettes",
                rightIcon = Icons.Default.Add,
                onRightClick = { navController.navigate(Screen.CreateRecipeScreen.route) },
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        containerColor = gris
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            LazyColumn {
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
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = blanc),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = noir,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onEdit
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Modifier",
                        tint = noir
                    )
                }

                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Supprimer",
                        tint = noir)
                }
            }

            Text(
                text = "${if(recipe.ingredients.isEmpty()) "Aucun" else recipe.ingredients.size} ingrédients",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                color = noir
            )
        }
    }
}
