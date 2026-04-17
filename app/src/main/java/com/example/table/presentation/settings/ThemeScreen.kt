package com.example.table.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.generalComponents.BottomBar
import com.example.table.presentation.generalComponents.TopBar
import com.example.table.presentation.settings.components.SettingsActionButtons

@Composable
fun ThemeScreen(
    navController: NavController,
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val isBrownTheme by viewModel.theme.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                label = "Thème",
                leftIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onLeftClick = { navController.navigate(Screen.SettingScreen.route) },
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
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Text(
                        text = "Choisissez un thème",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.weight(1f)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Thème Classique",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary,
                        )

                        Switch(
                            checked = isBrownTheme,
                            onCheckedChange = { value -> viewModel.onThemeChange(value) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary,
                                checkedTrackColor = MaterialTheme.colorScheme.tertiary,
                                uncheckedThumbColor = MaterialTheme.colorScheme.tertiary,
                                uncheckedTrackColor = MaterialTheme.colorScheme.secondary,
                                uncheckedBorderColor = MaterialTheme.colorScheme.tertiary
                            )
                        )

                        Text(
                            text = "Thème Noisette",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SettingsActionButtons(
                        onSave = {
                            viewModel.saveTheme(isBrownTheme)
                        },
                    )
                }
            }
        }
    }
}