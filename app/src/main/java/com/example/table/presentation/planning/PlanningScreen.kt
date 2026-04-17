package com.example.table.presentation.planning

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.generalComponents.BottomBar
import com.example.table.presentation.planning.components.DayCard
import com.example.table.presentation.generalComponents.TopBar

@Composable
fun PlanningScreen(
    navController: NavController,
    viewModel: PlanningViewModel = hiltViewModel()
) {
    val startDate by viewModel.startDate.collectAsState()
    val weekMeals by viewModel.mealsForWeek.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                label = viewModel.getPeriodLabel(startDate),
                leftIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                rightIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onLeftClick = { viewModel.previousPeriod() },
                onRightClick = { viewModel.nextPeriod() }
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(weekMeals) { day ->
                    DayCard(
                        dayMeals = day,
                        onAddMealClick = { selectedDay ->
                            navController.navigate(
                                Screen.AddEditPlanningScreen.route + "/$selectedDay"
                            )
                        },
                        onSlotClick = { slot, date ->
                            navController.navigate(
                                Screen.SlotDetailsScreen.route + "/$slot/$date"
                            )
                        }
                    )
                }
            }
        }
    }
}