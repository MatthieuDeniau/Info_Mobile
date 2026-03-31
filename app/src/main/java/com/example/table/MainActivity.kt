package com.example.table

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.table.navigation.Screen
import com.example.table.navigation.initTestRecipes
import com.example.table.presentation.list.*
import com.example.table.presentation.list.RecipeDetailsScreen
import com.example.table.ui.theme.ÀTableTheme
import dagger.hilt.android.AndroidEntryPoint

val blanc : Color = Color(250,250,250)
val gris : Color = Color(240, 240, 240)
val noir : Color = Color(0,0,0)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTestRecipes(this) //fonction pour initialiser les recettes et ingredients a supp une fois la fonctionnalite implementer

        enableEdgeToEdge()
        setContent {
            ÀTableTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.PlanningScreen.route
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
                }
            }
        }
    }
}
