package com.example.table.utils

import com.example.table.presentation.IngredientVM
import com.example.table.presentation.RecipeVM

private var recipeIdCounter = 0

fun generateRecipeId(): Int {
    return recipeIdCounter++
}

fun getRecipeById(id: Int): RecipeVM? {
    return recipeData.find { it.id == id }
}

fun getRecipes() : List<RecipeVM> {
    return recipeData
}

val recipeData: MutableList<RecipeVM> = mutableListOf(
    RecipeVM(
        id = generateRecipeId(),
        name = "Pâtes Carbonara",
        ingredients = listOf(
            IngredientVM(name = "Pâtes"),
            IngredientVM(name = "Lardons"),
            IngredientVM(name = "Œufs"),
            IngredientVM(name = "Parmesan"),
            IngredientVM(name = "Poivre")
        ),
        instructions = "Cuire les pâtes. Faire revenir les lardons. Mélanger œufs et parmesan. Ajouter aux pâtes hors du feu.",
        lastMade = null
    ),
    RecipeVM(
        id = generateRecipeId(),
        name = "Salade César",
        ingredients = listOf(
            IngredientVM(name = "Laitue romaine"),
            IngredientVM(name = "Poulet grillé"),
            IngredientVM(name = "Croûtons"),
            IngredientVM(name = "Parmesan"),
            IngredientVM(name = "Sauce César")
        ),
        instructions = "Couper la laitue. Ajouter poulet, croûtons et le parmesan. Mélanger avec la sauce.",
        lastMade = null
    ),
    RecipeVM(
        id = generateRecipeId(),
        name = "Omelette Fromage",
        ingredients = listOf(
            IngredientVM(name = "Œufs"),
            IngredientVM(name = "Fromage râpé"),
            IngredientVM(name = "Sel"),
            IngredientVM(name = "Poivre"),
            IngredientVM(name = "Beurre")
        ),
        instructions = "Battre les œufs. Faire fondre le beurre. Verser les œufs, ajouter le fromage, plier et servir.",
        lastMade = null
    ),
    RecipeVM(
        id = generateRecipeId(),
        name = "Poulet Teriyaki",
        ingredients = listOf(
            IngredientVM(name = "Poulet"),
            IngredientVM(name = "Sauce soja"),
            IngredientVM(name = "Sucre"),
            IngredientVM(name = "Gingembre"),
            IngredientVM(name = "Ail")
        ),
        instructions = "Mélanger sauce soja, sucre, gingembre et ail. Cuire le poulet puis ajouter la sauce jusqu’à épaississement.",
        lastMade = null
    ),
    RecipeVM(
        id = generateRecipeId(),
        name = "Soupe Tomate Maison",
        ingredients = listOf(
            IngredientVM(name = "Tomates"),
            IngredientVM(name = "Oignon"),
            IngredientVM(name = "Ail"),
            IngredientVM(name = "Bouillon"),
            IngredientVM(name = "Crème")
        ),
        instructions = "Faire revenir oignon et ail. Ajouter tomates et bouillon. Cuire puis mixer. Ajouter un peu de crème.",
        lastMade = null
    )
)
