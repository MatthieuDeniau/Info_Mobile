package com.example.table.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.table.presentation.MealVM
import com.example.table.utils.deleteMealFromList
import com.example.table.utils.getMeals

sealed class MealEvent {
    data class Delete(val meal: MealVM) : MealEvent()
}
class ListMealsViewModel : ViewModel() {
    private val _meals : MutableState<List<MealVM>> = mutableStateOf(emptyList())

    var meals : State<List<MealVM>> = _meals

    init {
        _meals.value = loadMeals()
    }

    private fun loadMeals() : List<MealVM> {
        return getMeals()
    }

    fun onEvent(event: MealEvent) {
        when (event) {
            is MealEvent.Delete -> {
                deleteMeal(event.meal)
            }
        }
    }

    private fun deleteMeal(meal : MealVM) {
        _meals.value = _meals.value.filter { it != meal }
        deleteMealFromList(meal)
    }
}