package com.example.table.navigation

sealed class Screen(val route: String) {
    data object MealListScreen : Screen(route = "meals_list_screen")
    data object MenuScreen : Screen(route = "menu_screen")
    data object AddEditMealScreen : Screen(route = "add_edit_meals_screen")
}