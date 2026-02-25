package com.example.table.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.table.presentation.MealVM
import com.example.table.utils.deleteMealFromList
import com.example.table.utils.getMeals

sealed class MenuEvent {

}

class MenuViewModel : ViewModel() {
    private val _meals = mutableStateOf(MealVM())
    val meals : State<MealVM> = _meals


    private fun loadMeals() : List<MealVM> {
        return getMeals()
    }

    fun onEvent(event: MealEvent) {

    }

}