package com.example.table.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val recipeId: Int,
    val date: LocalDate,
    val slot: Int// 1 = matin, 2 = midi, 3 = goûter, 4 = soir
)