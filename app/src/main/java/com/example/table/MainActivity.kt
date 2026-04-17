package com.example.table

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.table.navigation.Screen
import com.example.table.navigation.initTestRecipes
import com.example.table.presentation.planning.*
import com.example.table.presentation.recipe.CreateRecipeScreen
import com.example.table.presentation.recipe.EditRecipeScreen
import com.example.table.presentation.recipe.RecipeListScreen
import com.example.table.presentation.start.AppEntryViewModel
import com.example.table.presentation.settings.SettingScreen
import com.example.table.presentation.settings.ThemeScreen
import com.example.table.ui.theme.ATableTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    private fun askNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED -> {

                }

                ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS) -> {
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }

                else -> {
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()

        enableEdgeToEdge()

        setContent {
            ATableTheme {
                val backgroundColor = MaterialTheme.colorScheme.background

                window.decorView.setBackgroundColor(backgroundColor.toArgb())

                val navController = rememberNavController()
                val appEntryViewModel: AppEntryViewModel = hiltViewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screen.PlanningScreen.route,
                    enterTransition = {
                        fadeIn(animationSpec = tween(400))
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(400))
                    },
                    popEnterTransition = {
                        fadeIn(animationSpec = tween(400))
                    },
                    popExitTransition = {
                        fadeOut(animationSpec = tween(400))
                    }
                ){
                    composable (route = Screen.PlanningScreen.route) {
                        PlanningScreen(navController)
                    }
                    composable (
                        route = Screen.AddEditPlanningScreen.route + "/{day}",
                        arguments = listOf(
                            navArgument("day") { type = NavType.StringType }
                        )
                    ) {
                        AddEditPlanningScreen(navController)
                    }
                    composable(
                        route = Screen.SlotDetailsScreen.route + "/{slot}/{date}",
                        arguments = listOf(
                            navArgument("slot") { type = NavType.IntType },
                            navArgument("date") { type = NavType.StringType }
                        )
                    ) {
                        SlotDetailsScreen(navController)
                    }
                    composable(
                        route = Screen.RecipeDetailsScreen.route + "/{recipeId}",
                        arguments = listOf(
                            navArgument("recipeId") { type = NavType.IntType }
                        )
                    ) {
                        RecipeDetailsScreen(navController)
                    }
                    composable(
                        route = Screen.RecipeListScreen.route,
                    ) {
                        RecipeListScreen(navController)
                    }
                    composable(
                        route = Screen.CreateRecipeScreen.route,
                    ) {
                        CreateRecipeScreen(navController)
                    }
                    composable(
                        route = Screen.EditRecipeScreen.route + "/{recipeId}",
                        arguments = listOf(
                            navArgument("recipeId") { type = NavType.IntType }
                        )
                    ) {
                        EditRecipeScreen(navController)
                    }
                    composable(
                        route = Screen.SettingScreen.route,
                    ) {
                        SettingScreen(navController)
                    }
                    composable(
                        route = Screen.ThemeScreen.route,
                    ) {
                        ThemeScreen(navController)
                    }
                }
            }
        }
    }
}
