package com.example.table.presentation.start

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.local.AppDatabase
import com.example.table.data.repository.SettingsRepository
import com.example.table.data.repository.StartRepository
import com.example.table.domain.model.SettingsEntity
import com.example.table.navigation.initTestRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class AppEntryViewModel @Inject constructor(
    private val startCases: StartRepository,
    private val repositoryCases: SettingsRepository,
    @ApplicationContext context: Context,
) : ViewModel() {

    init {
        viewModelScope.launch {
            repositoryCases.getSettings().collect { settings ->
                if (settings == null) {
                    repositoryCases.saveSettings(
                        SettingsEntity(
                            allNotificationsEnabled = true,
                            morningEnabled = true, morningTime = "08:00",
                            noonEnabled = true, noonTime = "12:00",
                            snackEnabled = true, snackTime = "16:00",
                            eveningEnabled = true, eveningTime = "19:00"
                        )
                    )
                    checkLastMealPlan()
                    startCases.scheduleAllReminders()
                } else if (settings.allNotificationsEnabled) {
                    checkLastMealPlan()
                    startCases.scheduleAllReminders()
                }
            }
        }
        initTestRecipes(context)
    }

    private fun checkLastMealPlan() {
        viewModelScope.launch {
            val lastMeal = startCases.getLastMealDate()

            if (lastMeal != null) {
                val days = ChronoUnit.DAYS.between(lastMeal, LocalDate.now())
                if (days >= 7) {
                    startCases.sendStartNotification(days)
                }
            } else {
                startCases.sendStartNotification(-1)
            }
        }
    }
}
