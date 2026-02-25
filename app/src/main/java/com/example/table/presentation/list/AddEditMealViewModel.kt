package com.example.table.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.table.presentation.MealVM
import com.example.table.utils.addOrUpdateMeal

sealed interface AddEditMealEvent {
    data class EnteredName(val name: String): AddEditMealEvent
    data class EnteredDescription(val description: String): AddEditMealEvent
    data class EnteredIngredients(val ingredients: String): AddEditMealEvent
    data class EnteredLastMade(val lastmade: String): AddEditMealEvent
    data class EnteredNextMade(val nextmade: String): AddEditMealEvent
    data object SaveStory: AddEditMealEvent
}

class AddEditMealViewModel() : ViewModel() {
    private val _meals = mutableStateOf(MealVM())
    val meals : State<MealVM> = _meals

    fun onEvent(event: AddEditMealEvent) {
        when (event) {
            is AddEditMealEvent.EnteredName -> {
                _meals.value = _meals.value.copy(name = event.name)
            }
            is AddEditMealEvent.EnteredDescription -> {
                _meals.value = _meals.value.copy(description = event.description)
            }
            is AddEditMealEvent.EnteredIngredients -> {
                _meals.value = _meals.value.copy(ingredients = event.ingredients.split(","))
            }
            is AddEditMealEvent.EnteredLastMade -> {
                _meals.value = _meals.value.copy(lastMade = event.lastmade)
            }
            is AddEditMealEvent.EnteredNextMade -> {
                _meals.value = _meals.value.copy(nextMade = event.nextmade)
            }
            is AddEditMealEvent.SaveStory -> {
                addOrUpdateMeal(meals.value)
            }
        }
    }
}