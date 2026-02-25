package com.example.table.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.table.navigation.Screen

@Composable
fun AddEditStoryScreen(
    navController: NavController,
    viewModel: AddEditMealViewModel
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton (
                onClick = {
                    viewModel.onEvent(AddEditMealEvent.SaveStory)
                    navController.navigate(
                        Screen.MealListScreen.route
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Save Meal"
                )
            }
        }
    ) { contentPadding ->
        val meal = viewModel.meals.value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            OutlinedTextField(
                value = meal.name,
                label = {
                    Text("Name")
                },
                onValueChange = {
                    viewModel.onEvent(AddEditMealEvent.EnteredName(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = meal.description,
                label = {
                    Text("Description")
                },
                onValueChange = {
                    viewModel.onEvent(AddEditMealEvent.EnteredDescription(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = meal.ingredients.joinToString(","),
                label = {
                    Text("Ingredients")
                },
                onValueChange = {
                    viewModel.onEvent(AddEditMealEvent.EnteredIngredients(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = meal.lastMade?:"None",
                label = {
                    Text("Last Made")
                },
                onValueChange = {
                    viewModel.onEvent(AddEditMealEvent.EnteredLastMade(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = meal.nextMade,
                label = {
                    Text("Next Made")
                },
                onValueChange = {
                    viewModel.onEvent(AddEditMealEvent.EnteredNextMade(it))
                },
                singleLine = false,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}