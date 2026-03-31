package com.example.table.navigation

import android.content.Context
import androidx.room.Room
import com.example.table.data.local.AppDatabase
import com.example.table.data.local.IngredientDao
import com.example.table.data.local.RecipeDao
import com.example.table.domain.model.IngredientEntity
import com.example.table.domain.model.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

suspend fun populateTestData(
    recipeDao: RecipeDao,
    ingredientDao: IngredientDao
) {
    val ingredientNames = listOf(
        "Pâtes (spaghetti)",
        "Pancetta",
        "Œufs",
        "Parmesan râpé",
        "Poivre noir",

        "Blancs de poulet",
        "Lait de coco",
        "Pâte de curry",
        "Oignons",
        "Ail",
        "Huile",

        "Laitue romaine",
        "Poulet grillé",
        "Croûtons",
        "Sauce César",

        "Pain à burger",
        "Steak haché",
        "Fromage cheddar",
        "Tomate",
        "Salade",
        "Sauce burger",

        "Feuilles de lasagne",
        "Viande hachée",
        "Sauce tomate",
        "Béchamel",
        "Mozzarella",

        "Pâte brisée",
        "Lardons",
        "Crème fraîche",
        "Gruyère râpé",

        "Riz arborio",
        "Champignons",
        "Bouillon de légumes",
        "Beurre",

        "Chocolat noir",
        "Sucre",
        "Farine",

        "Aubergines",
        "Courgettes",
        "Poivrons",
        "Tomates",

        "Fruits rouges congelés",
        "Banane",
        "Yaourt",
        "Granola",
        "Miel"
    )

    val ingredientIds = ingredientNames.map { name ->
        ingredientDao.insert(IngredientEntity(name = name)).toInt()
    }

    fun id(name: String): Int = ingredientIds[ingredientNames.indexOf(name)]

    val recipes = listOf(
        RecipeEntity(
            name = "Pâtes Carbonara",
            ingredients = listOf(
                id("Pâtes (spaghetti)"),
                id("Pancetta"),
                id("Œufs"),
                id("Parmesan râpé"),
                id("Poivre noir")
            ),
            instructions = "Cuire les pâtes, mélanger avec œufs, pancetta et parmesan.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Poulet au Curry",
            ingredients = listOf(
                id("Blancs de poulet"),
                id("Lait de coco"),
                id("Pâte de curry"),
                id("Oignons"),
                id("Ail"),
                id("Huile")
            ),
            instructions = "Saisir le poulet, ajouter oignons, ail, lait de coco et curry.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Salade César",
            ingredients = listOf(
                id("Laitue romaine"),
                id("Poulet grillé"),
                id("Croûtons"),
                id("Parmesan râpé"),
                id("Sauce César")
            ),
            instructions = "Mélanger la laitue, le poulet grillé, les croûtons et la sauce.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Burger Maison",
            ingredients = listOf(
                id("Pain à burger"),
                id("Steak haché"),
                id("Fromage cheddar"),
                id("Tomate"),
                id("Salade"),
                id("Sauce burger")
            ),
            instructions = "Griller le steak, ajouter fromage, légumes et sauce dans le pain.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Lasagnes",
            ingredients = listOf(
                id("Feuilles de lasagne"),
                id("Viande hachée"),
                id("Sauce tomate"),
                id("Béchamel"),
                id("Mozzarella")
            ),
            instructions = "Alterner couches de pâtes, viande, sauce tomate et béchamel.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Quiche Lorraine",
            ingredients = listOf(
                id("Pâte brisée"),
                id("Lardons"),
                id("Crème fraîche"),
                id("Œufs"),
                id("Gruyère râpé")
            ),
            instructions = "Mélanger lardons, crème, œufs et fromage, puis cuire au four.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Risotto aux Champignons",
            ingredients = listOf(
                id("Riz arborio"),
                id("Champignons"),
                id("Bouillon de légumes"),
                id("Parmesan râpé"),
                id("Oignons"),
                id("Beurre")
            ),
            instructions = "Cuire le riz lentement avec bouillon, champignons et parmesan.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Gâteau au Chocolat",
            ingredients = listOf(
                id("Chocolat noir"),
                id("Beurre"),
                id("Sucre"),
                id("Farine"),
                id("Œufs")
            ),
            instructions = "Mélanger chocolat fondu, beurre, sucre, farine et œufs.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Ratatouille",
            ingredients = listOf(
                id("Aubergines"),
                id("Courgettes"),
                id("Poivrons"),
                id("Tomates"),
                id("Oignons"),
                id("Ail")
            ),
            instructions = "Mijoter les légumes coupés en dés dans une cocotte.",
            lastMade = null
        ),

        RecipeEntity(
            name = "Smoothie Bowl",
            ingredients = listOf(
                id("Fruits rouges congelés"),
                id("Banane"),
                id("Yaourt"),
                id("Granola"),
                id("Miel")
            ),
            instructions = "Mixer les fruits avec le yaourt, servir avec granola et miel.",
            lastMade = null
        )
    )

    recipes.forEach { recipeDao.insertRecipe(it) }
}

fun initTestRecipes(context: Context) {
    val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    CoroutineScope(Dispatchers.IO).launch {
        val recipeDao = db.recipeDao

        val ingredientDao = db.ingredientDao

        val count = recipeDao.getRecipeCount()

        if (count == 0) {
            populateTestData(recipeDao, ingredientDao)
        }
    }
}
