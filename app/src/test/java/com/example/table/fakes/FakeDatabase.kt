package com.example.table.fakes

import com.example.table.data.local.IngredientDao
import com.example.table.data.local.MealDao
import com.example.table.data.local.RecipeDao
import com.example.table.data.local.SettingsDao
import com.example.table.domain.model.IngredientEntity
import com.example.table.domain.model.MealEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.domain.model.SettingsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class FakeDatabase : MealDao, RecipeDao, IngredientDao, SettingsDao {
    val meals = MutableStateFlow<List<MealEntity>>(emptyList())
    val recipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val ingredients = MutableStateFlow<List<IngredientEntity>>(emptyList())
    private val settings = MutableStateFlow<SettingsEntity?>(null)

    override fun getAllMeals(): Flow<List<MealEntity>> = meals
    
    override suspend fun getMealByRecipeId(recipeId: Int): MealEntity? {
        return meals.value.find { it.recipeId == recipeId }
    }

    override suspend fun getLastMealDate(): LocalDate? {
        return meals.value.map { it.date }.maxOrNull()
    }

    override suspend fun isMealPlannedForType(date: String, type: Int): Boolean {
        return meals.value.any { it.date.toString() == date && it.slot == type }
    }

    override suspend fun insert(meal: MealEntity) {
        val id = if (meal.id == 0) (meals.value.maxOfOrNull { it.id } ?: 0) + 1 else meal.id
        meals.update { it + meal.copy(id = id) }
    }

    override suspend fun update(meal: MealEntity) {
        meals.update { list ->
            list.map { if (it.id == meal.id) meal else it }
        }
    }

    override suspend fun delete(meal: MealEntity) {
        meals.update { list ->
            list.filterNot { it.id == meal.id }
        }
    }

    override fun getAllRecipes(): Flow<List<RecipeEntity>> = recipes

    override fun getRecipeById(id: Int): Flow<RecipeEntity> {
        return recipes.map { list -> list.first { it.id == id } }
    }

    override suspend fun getRecipeByIdOnce(id: Int): RecipeEntity? {
        return recipes.value.find { it.id == id }
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        val id = if (recipe.id == 0) (recipes.value.maxOfOrNull { it.id } ?: 0) + 1 else recipe.id
        recipes.update { it + recipe.copy(id = id) }
    }

    override suspend fun getRecipeCount(): Int = recipes.value.size

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        recipes.update { list ->
            list.map { if (it.id == recipe.id) recipe else it }
        }
    }

    override suspend fun delete(recipe: RecipeEntity) {
        recipes.update { list ->
            list.filterNot { it.id == recipe.id }
        }
    }

    suspend fun deleteAllRecipes() {
        recipes.value = emptyList()
    }

    override fun getAllIngredients(): Flow<List<IngredientEntity>> = ingredients

    override suspend fun getIngredientById(id: Int): IngredientEntity? {
        return ingredients.value.find { it.id == id }
    }

    override suspend fun insert(ingredient: IngredientEntity): Long {
        val newId = (ingredients.value.maxOfOrNull { it.id } ?: 0) + 1
        val newIngredient = ingredient.copy(id = newId)
        ingredients.update { it + newIngredient }
        return newId.toLong()
    }

    override suspend fun delete(ingredient: IngredientEntity) {
        ingredients.update { list ->
            list.filterNot { it.id == ingredient.id }
        }
    }

    override suspend fun deleteAll() {
        ingredients.value = emptyList()
    }

    override fun getSettings(): Flow<SettingsEntity?> = settings

    override suspend fun getSetting(): SettingsEntity? = settings.value

    override suspend fun saveSettings(settings: SettingsEntity) {
        this.settings.value = settings
    }
}
