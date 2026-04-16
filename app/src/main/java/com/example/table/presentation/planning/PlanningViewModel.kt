package com.example.table.presentation.planning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.PlanningRepository
import com.example.table.presentation.MealVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

data class DayMeals(
    val date: LocalDate,
    val mealsBySlot: Map<Int, List<MealVM>>
)

@HiltViewModel
class PlanningViewModel @Inject constructor(
    private val useCases: PlanningRepository
) : ViewModel() {

    private val _startDate = MutableStateFlow(LocalDate.now(ZoneId.of("America/Toronto")))
    val startDate: StateFlow<LocalDate> = _startDate.asStateFlow()

    fun nextPeriod() {
        _startDate.value = useCases.getNextPeriod(_startDate.value)
    }

    fun previousPeriod() {
        _startDate.value = useCases.getPreviousPeriod(_startDate.value)
    }

    val mealsForWeek: StateFlow<List<DayMeals>> =
        _startDate.flatMapLatest { startDate ->
            useCases.getMealsForWeek(startDate)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun getPeriodLabel(startDate: LocalDate): String {
        return useCases.formatPeriodLabel(startDate)
    }

    fun deleteMeal(meal: MealVM) {
        viewModelScope.launch {
            useCases.deleteMeal(meal)
        }
    }
}
