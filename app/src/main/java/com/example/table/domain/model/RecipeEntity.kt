package com.example.table.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val ingredients: List<Int>,
    val instructions: String,
    val lastMade: String?
)