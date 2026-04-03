package com.example.table.navigation

sealed class Screen(val route: String) {
    data object PlanningScreen : Screen(route = "planning_screen")
    data object AddEditPlanningScreen : Screen(route = "add_edit_planning")
    data object SlotDetailsScreen : Screen(route = "slot_details")
    data object RecipeDetailsScreen : Screen("recipe_details")
    data object RecipeListScreen : Screen("recipe_list")
    data object CreateRecipeScreen : Screen("create_recipe")
    data object EditRecipeScreen : Screen("edit_recipe")
}
