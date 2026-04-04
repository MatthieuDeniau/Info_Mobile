package com.example.table.presentation.planning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
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
import com.example.table.gris
import com.example.table.noir
import com.example.table.blanc
import com.example.table.navigation.Screen
import com.example.table.presentation.planning.components.TopBar
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
        containerColor = gris
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formattedDate,
                fontSize = 16.sp,
                color = noir.copy(alpha = 0.6f),
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
                        color = noir.copy(alpha = 0.5f)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(meals) { meal ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.RecipeDetailsScreen.route + "/${meal.recipe.id}"
                                    )
                                },
                            colors = CardDefaults.cardColors(containerColor = blanc),
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
                                    color = noir,
                                    modifier = Modifier.weight(1f)
                                )

                                IconButton(
                                    onClick = { viewModel.deleteMeal(meal) }
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
                }
            }
        }
    }
}
