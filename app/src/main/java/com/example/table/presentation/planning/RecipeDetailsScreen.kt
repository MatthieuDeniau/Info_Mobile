package com.example.table.presentation.planning

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.table.presentation.formatQuantity
import com.example.table.presentation.generalComponents.TopBar

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
        containerColor = MaterialTheme.colorScheme.secondary
    ) { padding ->
        if (recipe != null) {
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
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = recipe!!.name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Ingrédients",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }

                    items(recipe!!.ingredients) { ingredient ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = ingredient.name,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.weight(1f)
                                )

                                Text(
                                    text = ingredient.formatQuantity(),
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.tertiary
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
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = recipe!!.instructions,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.tertiary
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
}