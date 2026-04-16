package com.example.table

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
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
import com.example.table.presentation.notification.NotificationScreen
import com.example.table.presentation.planning.*
import com.example.table.presentation.recipe.CreateRecipeScreen
import com.example.table.presentation.recipe.EditRecipeScreen
import com.example.table.presentation.recipe.RecipeListScreen
import com.example.table.presentation.start.AppEntryViewModel
import com.example.table.presentation.settings.SettingScreen
import com.example.table.ui.theme.ÀTableTheme
import dagger.hilt.android.AndroidEntryPoint

val blanc : Color = Color(250,165,112)
val gris : Color = Color(156, 98, 44)
val noir : Color = Color(0,0,0)

/*val blanc : Color = Color(250,250,250)
val gris : Color = Color(240, 240, 240)
val noir : Color = Color(0,0,0)*/

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
        initTestRecipes(this)

        enableEdgeToEdge()

        setContent {
            ÀTableTheme {

                val navController = rememberNavController()
                val appEntryViewModel: AppEntryViewModel = hiltViewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screen.PlanningScreen.route//Screen.NotificationScreen.route
                ){
                    composable(route = Screen.NotificationScreen.route) {
                        NotificationScreen(navController)
                    }
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
                }
            }
        }
    }
}
