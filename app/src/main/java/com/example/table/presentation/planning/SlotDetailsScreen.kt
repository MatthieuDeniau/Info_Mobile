package com.example.table.presentation.planning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.generalComponents.TopBar
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SlotDetailsScreen(
    navController: NavController,
    viewModel: SlotDetailsViewModel = hiltViewModel()
) {
    val meals by viewModel.meals.collectAsState()
    val slotLabel = viewModel.getSlotLabel()
    val date by viewModel.date
    val formattedDate = date.format(
        DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH)
    ).replaceFirstChar { it.uppercase() }

    Scaffold(
        topBar = {
            TopBar(
                label = slotLabel,
                rightIcon = Icons.Default.Close,
                onRightClick = { navController.popBackStack() }
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
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formattedDate,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                if (meals.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Aucun repas pour ce moment.",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(meals) { meal ->
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        navController.navigate(
                                            Screen.RecipeDetailsScreen.route + "/${meal.recipe.id}"
                                        )
                                    },
                                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = meal.recipe.name,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        modifier = Modifier.weight(1f)
                                    )

                                    IconButton(
                                        onClick = { viewModel.deleteMeal(meal) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = "Supprimer",
                                            tint = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}