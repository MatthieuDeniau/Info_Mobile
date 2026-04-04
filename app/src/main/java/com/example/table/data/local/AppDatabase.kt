package com.example.table.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.table.domain.model.MealEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.domain.model.IngredientEntity

@Database(
    entities = [
        RecipeEntity::class,
        MealEntity::class,
        IngredientEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    IntListConverter::class,
    LocalDateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val mealDao: MealDao
    abstract val ingredientDao: IngredientDao

    companion object {
        const val DATABASE_NAME = "mealplanner.db"
    }
}