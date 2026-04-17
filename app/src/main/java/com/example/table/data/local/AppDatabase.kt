package com.example.table.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.table.domain.model.MealEntity
import com.example.table.domain.model.RecipeEntity
import com.example.table.domain.model.IngredientEntity
import com.example.table.domain.model.SettingsEntity
import com.example.table.domain.model.ThemeEntity

@Database(
    entities = [
        RecipeEntity::class,
        MealEntity::class,
        IngredientEntity::class,
        SettingsEntity::class,
        ThemeEntity::class
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
    abstract val settingsDao: SettingsDao
    abstract val themeDao: ThemeDao

    companion object {
        const val DATABASE_NAME = "mealplanner.db"
    }
}