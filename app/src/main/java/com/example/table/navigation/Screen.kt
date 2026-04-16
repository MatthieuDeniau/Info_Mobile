package com.example.table.navigation

sealed class Screen(val route: String) {
    data object PlanningScreen : Screen(route = "planning_screen")
    data object AddEditPlanningScreen : Screen(route = "add_edit_planning")
    data object SlotDetailsScreen : Screen(route = "slot_details")
    data object RecipeDetailsScreen : Screen(route = "recipe_details")
    data object RecipeListScreen : Screen(route = "recipe_list")
    data object CreateRecipeScreen : Screen(route = "create_recipe")
    data object EditRecipeScreen : Screen(route = "edit_recipe")
    data object SettingScreen : Screen(route = "setting_screen")
    data object NotificationScreen : Screen(route = "notification_screen")
}
