package com.example.table

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.table.navigation.Screen
import com.example.table.presentation.list.AddEditMealViewModel
import com.example.table.presentation.list.AddEditStoryScreen
import com.example.table.presentation.list.ListMealScreen
import com.example.table.presentation.list.ListMealsViewModel
import com.example.table.presentation.list.MenuScreen
import com.example.table.presentation.list.MenuViewModel
import com.example.table.ui.theme.ÀTableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ÀTableTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .background(
                            Color(red = 250, blue = 112, green = 165),
                        ),
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MealListScreen.route,
                        modifier = Modifier
                            .padding(innerPadding)
                    ){
                        composable (route = Screen.MealListScreen.route) {
                            val meals = viewModel<ListMealsViewModel>()
                            ListMealScreen(navController, meals)
                        }
                        composable (route = Screen.AddEditMealScreen.route) {
                            val meals = viewModel<AddEditMealViewModel>()
                            AddEditStoryScreen(navController, meals)
                        }
                        composable (route = Screen.MenuScreen.route) {
                            val meals = viewModel<MenuViewModel>()
                            MenuScreen(navController, meals)
                        }
                    }
                }
            }
        }
    }
}
