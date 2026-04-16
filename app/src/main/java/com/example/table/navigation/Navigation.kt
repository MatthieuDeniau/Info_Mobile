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
    val ingredientsData = listOf(
        IngredientEntity(name = "Pâtes (spaghetti)", quantity = 200.0, unit = "g"),
        IngredientEntity(name = "Pancetta", quantity = 100.0, unit = "g"),
        IngredientEntity(name = "Œufs", quantity = 2.0, unit = ""),
        IngredientEntity(name = "Parmesan râpé", quantity = 50.0, unit = "g"),
        IngredientEntity(name = "Poivre noir", quantity = 1.0, unit = "c. à café"),

        IngredientEntity(name = "Blancs de poulet", quantity = 300.0, unit = "g"),
        IngredientEntity(name = "Lait de coco", quantity = 200.0, unit = "ml"),
        IngredientEntity(name = "Pâte de curry", quantity = 2.0, unit = "c. à soupe"),
        IngredientEntity(name = "Oignons", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Ail", quantity = 2.0, unit = "gousses"),
        IngredientEntity(name = "Huile", quantity = 1.0, unit = "c. à soupe"),

        IngredientEntity(name = "Laitue romaine", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Poulet grillé", quantity = 150.0, unit = "g"),
        IngredientEntity(name = "Croûtons", quantity = 30.0, unit = "g"),
        IngredientEntity(name = "Sauce César", quantity = 3.0, unit = "c. à soupe"),

        IngredientEntity(name = "Pain à burger", quantity = 2.0, unit = ""),
        IngredientEntity(name = "Steak haché", quantity = 240.0, unit = "g"),
        IngredientEntity(name = "Fromage cheddar", quantity = 2.0, unit = "tranches"),
        IngredientEntity(name = "Tomate", quantity = 2.0, unit = "tranches"),
        IngredientEntity(name = "Salade", quantity = null, unit = "Quelques feuilles"),
        IngredientEntity(name = "Sauce burger", quantity = 2.0, unit = "c. à soupe"),

        IngredientEntity(name = "Feuilles de lasagne", quantity = 6.0, unit = ""),
        IngredientEntity(name = "Viande hachée", quantity = 300.0, unit = "g"),
        IngredientEntity(name = "Sauce tomate", quantity = 200.0, unit = "ml"),
        IngredientEntity(name = "Béchamel", quantity = 150.0, unit = "ml"),
        IngredientEntity(name = "Mozzarella", quantity = 100.0, unit = "g"),

        IngredientEntity(name = "Pâte brisée", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Lardons", quantity = 150.0, unit = "g"),
        IngredientEntity(name = "Crème fraîche", quantity = 200.0, unit = "ml"),
        IngredientEntity(name = "Gruyère râpé", quantity = 80.0, unit = "g"),

        IngredientEntity(name = "Riz arborio", quantity = 200.0, unit = "g"),
        IngredientEntity(name = "Champignons", quantity = 150.0, unit = "g"),
        IngredientEntity(name = "Bouillon de légumes", quantity = 500.0, unit = "ml"),
        IngredientEntity(name = "Beurre", quantity = 20.0, unit = "g"),

        IngredientEntity(name = "Chocolat noir", quantity = 100.0, unit = "g"),
        IngredientEntity(name = "Sucre", quantity = 80.0, unit = "g"),
        IngredientEntity(name = "Farine", quantity = 60.0, unit = "g"),

        IngredientEntity(name = "Aubergines", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Courgettes", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Poivrons", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Tomates", quantity = 2.0, unit = ""),

        IngredientEntity(name = "Fruits rouges congelés", quantity = 150.0, unit = "g"),
        IngredientEntity(name = "Banane", quantity = 1.0, unit = ""),
        IngredientEntity(name = "Yaourt", quantity = 100.0, unit = "g"),
        IngredientEntity(name = "Granola", quantity = 30.0, unit = "g"),
        IngredientEntity(name = "Miel", quantity = 1.0, unit = "c. à soupe")
    )

    val insertedIngredients = ingredientsData.map { entity ->
        val id = ingredientDao.insert(entity).toInt()
        entity.copy(id = id)
    }

    fun id(name: String): Int = insertedIngredients.find { it.name == name }?.id ?: 0

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
            lastMade = "2024-02-20"
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
            lastMade = "2024-02-10"
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
            lastMade = ""
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
            lastMade = "2024-01-28"
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
            lastMade = "2024-12-15"
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
            lastMade = ""
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
            lastMade = "2024-02-17"
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
            lastMade = "2024-11-02"
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
            lastMade = "2024-01-05"
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

suspend fun clearAllData(
    recipeDao: RecipeDao,
    ingredientDao: IngredientDao
) {
    ingredientDao.deleteAll()
    recipeDao.deleteAll()
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

        //clearAllData(recipeDao, ingredientDao)

        val count = recipeDao.getRecipeCount()

        if (count == 0) {
            populateTestData(recipeDao, ingredientDao)
        }
    }
}
