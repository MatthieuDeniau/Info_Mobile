package com.example.table.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.table.domain.model.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE recipeId = :recipeId")
    suspend fun getMealByRecipeId(recipeId: Int): MealEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: MealEntity)

    @Update
    suspend fun update(meal: MealEntity)

    @Delete
    suspend fun delete(meal: MealEntity)
}