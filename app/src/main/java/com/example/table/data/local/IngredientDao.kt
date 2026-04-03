package com.example.table.data.local

import androidx.room.*
import com.example.table.domain.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredients ORDER BY name ASC")
    fun getAllIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM ingredients WHERE id = :id")
    suspend fun getIngredientById(id: Int): IngredientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: IngredientEntity): Long

    @Delete
    suspend fun delete(ingredient: IngredientEntity)

    @Query("DELETE FROM ingredients")
    suspend fun deleteAll()
}