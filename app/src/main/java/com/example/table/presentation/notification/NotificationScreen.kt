package com.example.table.presentation.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.table.navigation.Screen

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = hiltViewModel()
){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.showSimpleNotification() }) {
            Text(text = "Show Notification")
        }
        Button(onClick = { viewModel.updateNotification() }) {
            Text(text = "Update Notification")
        }
        Button(onClick = { viewModel.cancelNotification() }) {
            Text(text = "Cancel Notification")
        }
        Button(onClick = { navController.navigate(Screen.PlanningScreen.route) }) {
            Text(text = "Lauchn App")
        }
    }
}