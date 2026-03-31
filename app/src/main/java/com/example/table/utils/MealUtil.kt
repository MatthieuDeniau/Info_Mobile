package com.example.table.utils

import com.example.table.presentation.MealVM
import java.time.LocalDate

private var mealIdCounter = 0

fun generateMealId(): Int {
    return mealIdCounter++
}

fun getMeals() : List<MealVM> {
    return mealData
}

fun addOrUpdateMeal(meal: MealVM) {
    val existingMeal = mealData.find { it.id == meal.id }

    existingMeal?.let { mealData.remove(it) }

    mealData.add(meal)
}

fun deleteMealFromList(meal : MealVM) {
    mealData.remove(meal)
}

val mealData: MutableList<MealVM> = mutableListOf(
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-01"),
        recipe = getRecipeById(0)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-02"),
        recipe = getRecipeById(1)!!,
        slot = 1
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-03"),
        recipe = getRecipeById(2)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-04"),
        recipe = getRecipeById(3)!!,
        slot = 1
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-05"),
        recipe = getRecipeById(4)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-06"),
        recipe = getRecipeById(0)!!,
        slot = 1
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-07"),
        recipe = getRecipeById(1)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-08"),
        recipe = getRecipeById(2)!!,
        slot = 1
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-09"),
        recipe = getRecipeById(3)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-10"),
        recipe = getRecipeById(4)!!,
        slot = 1
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-11"),
        recipe = getRecipeById(0)!!,
        slot = 0
    ),
    MealVM(
        id = generateMealId(),
        date = LocalDate.parse("2026-02-12"),
        recipe = getRecipeById(1)!!,
        slot = 1
    )
)
