package com.example.table.presentation

data class MealVM (
    val id: Int = -1,
    val name: String = "NONE",
    val description: String = "NONE",
    val ingredients: List<String> = emptyList(),
    val lastMade: String? = null,
    val nextMade: String,
)
