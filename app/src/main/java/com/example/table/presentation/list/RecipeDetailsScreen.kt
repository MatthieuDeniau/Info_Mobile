package com.example.table.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.blanc
import com.example.table.gris
import com.example.table.noir
import com.example.table.presentation.components.TopBar
import com.example.table.presentation.list.RecipeDetailsViewModel

@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val recipe by viewModel.recipe.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                label = "Détails de la recette",
                rightIcon = Icons.Default.Close,
                onRightClick = { navController.popBackStack() }
            )
        },
        containerColor = gris
    ) { padding ->
        if (recipe != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(
                        text = recipe!!.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = noir
                    )
                }

                item {
                    Text(
                        text = "Ingrédients",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = noir
                    )
                }

                items(recipe!!.ingredients) { ingredient ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = blanc),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "• ${ingredient.name}",
                                fontSize = 16.sp,
                                color = noir
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Instructions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = noir
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = blanc),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = recipe!!.instructions,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp,
                            color = noir
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}