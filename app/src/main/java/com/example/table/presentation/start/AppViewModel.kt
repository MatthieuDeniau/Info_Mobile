package com.example.table.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.NotificationRepository
import com.example.table.data.repository.StartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class AppEntryViewModel @Inject constructor(
    private val useCases: StartRepository,
) : ViewModel() {

    init {
        checkLastMealPlan()
        viewModelScope.launch {
            useCases.scheduleAllReminders()
        }
    }

    private fun checkLastMealPlan() {
        viewModelScope.launch {
            val lastMeal = useCases.getLastMealDate()

            if (lastMeal != null) {
                val days = ChronoUnit.DAYS.between(lastMeal, LocalDate.now())
                if (days >= 7) {
                    useCases.sendStartNotification(days)
                }
            } else {
                useCases.sendStartNotification(-1)
            }
        }
    }
}
