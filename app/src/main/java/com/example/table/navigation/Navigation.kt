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

    // Liste complète des ingrédients AVEC quantités
    val ingredientsData = listOf(
        "Pâtes (spaghetti)" to "200 g",
        "Pancetta" to "100 g",
        "Œufs" to "2",
        "Parmesan râpé" to "50 g",
        "Poivre noir" to "1 c. à café",

        "Blancs de poulet" to "300 g",
        "Lait de coco" to "200 ml",
        "Pâte de curry" to "2 c. à soupe",
        "Oignons" to "1",
        "Ail" to "2 gousses",
        "Huile" to "1 c. à soupe",

        "Laitue romaine" to "1",
        "Poulet grillé" to "150 g",
        "Croûtons" to "30 g",
        "Sauce César" to "3 c. à soupe",

        "Pain à burger" to "2",
        "Steak haché" to "2 x 120 g",
        "Fromage cheddar" to "2 tranches",
        "Tomate" to "2 tranches",
        "Salade" to "Quelques feuilles",
        "Sauce burger" to "2 c. à soupe",

        "Feuilles de lasagne" to "6",
        "Viande hachée" to "300 g",
        "Sauce tomate" to "200 ml",
        "Béchamel" to "150 ml",
        "Mozzarella" to "100 g",

        "Pâte brisée" to "1",
        "Lardons" to "150 g",
        "Crème fraîche" to "200 ml",
        "Gruyère râpé" to "80 g",

        "Riz arborio" to "200 g",
        "Champignons" to "150 g",
        "Bouillon de légumes" to "500 ml",
        "Beurre" to "20 g",

        "Chocolat noir" to "100 g",
        "Sucre" to "80 g",
        "Farine" to "60 g",

        "Aubergines" to "1",
        "Courgettes" to "1",
        "Poivrons" to "1",
        "Tomates" to "2",

        "Fruits rouges congelés" to "150 g",
        "Banane" to "1",
        "Yaourt" to "100 g",
        "Granola" to "30 g",
        "Miel" to "1 c. à soupe"
    )

    // Insertion des ingrédients avec quantité
    val ingredientIds = ingredientsData.map { (name, quantity) ->
        ingredientDao.insert(
            IngredientEntity(
                name = name,
                quantity = quantity
            )
        ).toInt()
    }

    // Helper pour récupérer l'ID d'un ingrédient
    fun id(name: String): Int =
        ingredientIds[ingredientsData.indexOfFirst { it.first == name }]

    // Recettes complètes
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