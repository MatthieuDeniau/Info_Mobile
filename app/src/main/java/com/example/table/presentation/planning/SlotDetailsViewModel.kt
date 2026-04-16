package com.example.table.presentation.planning

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.PlanningRepository
import com.example.table.presentation.MealVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SlotDetailsViewModel @Inject constructor(
    private val useCases: PlanningRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealVM>>(emptyList())
    val meals: StateFlow<List<MealVM>> = _meals.asStateFlow()

    private val _slot = mutableStateOf(1)
    val slot: State<Int> = _slot

    private val _date = mutableStateOf<LocalDate>(LocalDate.now())
    val date: State<LocalDate> = _date

    init {
        val slotId = savedStateHandle.get<Int>("slot") ?: 1
        val dateStr = savedStateHandle.get<String>("date")
        _slot.value = slotId
        _date.value = dateStr?.let { LocalDate.parse(it) } ?: LocalDate.now()
        
        loadMeals()
    }

    private fun loadMeals() {
        viewModelScope.launch {
            useCases.getMealsForSlot(_date.value, _slot.value).collect {
                _meals.value = it
            }
        }
    }

    fun getSlotLabel(): String {
        return useCases.getSlotLabel(_slot.value)
    }

    fun deleteMeal(meal: MealVM) {
        viewModelScope.launch {
            useCases.deleteMeal(meal)
        }
    }
}
