package com.example.table.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.generalComponents.BottomBar
import com.example.table.presentation.generalComponents.TopBar
import com.example.table.presentation.settings.components.*

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val allEnabled by viewModel.allNotificationsEnabled.collectAsState()
    val morningEnabled by viewModel.morningEnabled.collectAsState()
    val morningTime by viewModel.morningTime.collectAsState()
    val noonEnabled by viewModel.noonEnabled.collectAsState()
    val noonTime by viewModel.noonTime.collectAsState()
    val snackEnabled by viewModel.snackEnabled.collectAsState()
    val snackTime by viewModel.snackTime.collectAsState()
    val eveningEnabled by viewModel.eveningEnabled.collectAsState()
    val eveningTime by viewModel.eveningTime.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                label = "Notification",
                rightIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                onRightClick = { navController.navigate(Screen.ThemeScreen.route) }
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { padding ->
        Card(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    TitleSwitchSetting(
                        label = "Toutes les notifications",
                        value = allEnabled,
                        onValueChange = { allEnabled -> viewModel.onAllNotificationsEnabledChange(allEnabled) }
                    )
                }

                if (allEnabled) {
                    item {
                        RoutineSubCard(
                            title = "Routine du matin",
                            enabled = morningEnabled,
                            time = morningTime,
                            onEnabledChange = { morningEnabled -> viewModel.onMorningEnabledChange(morningEnabled) },
                            onTimeChange = { morningTime -> viewModel.onMorningTimeChange(morningTime) }
                        )
                    }
                    item {
                        RoutineSubCard(
                            title = "Routine du midi",
                            enabled = noonEnabled,
                            time = noonTime,
                            onEnabledChange = { noonEnabled -> viewModel.onNoonEnabledChange(noonEnabled) },
                            onTimeChange = { noonTime -> viewModel.onNoonTimeChange(noonTime) }
                        )
                    }
                    item {
                        RoutineSubCard(
                            title = "Routine du goûter",
                            enabled = snackEnabled,
                            time = snackTime,
                            onEnabledChange = { snackEnabled -> viewModel.onSnackEnabledChange(snackEnabled) },
                            onTimeChange = { snackTime -> viewModel.onSnackTimeChange(snackTime) }
                        )
                    }
                    item {
                        RoutineSubCard(
                            title = "Routine du soir",
                            enabled = eveningEnabled,
                            time = eveningTime,
                            onEnabledChange = { eveningEnabled -> viewModel.onEveningEnabledChange(eveningEnabled) },
                            onTimeChange = { eveningTime -> viewModel.onEveningTimeChange(eveningTime) }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SettingsActionButtons(
                        onSave = {
                            viewModel.saveSettings()
                        },
                    )
                }
            }
        }
    }
}