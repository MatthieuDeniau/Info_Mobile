package com.example.table.domain.usecases

import android.app.NotificationManager
import android.util.Log
import com.example.table.data.local.MealDao
import com.example.table.data.local.SettingsDao
import java.time.LocalDate
import javax.inject.Inject

class SendReminderNotificationUseCase @Inject constructor(
    private val planningDao: MealDao,
    private val settingsDao: SettingsDao,
    private val send : SendNotificationUseCase
) {
    suspend operator fun invoke(type: Int) {
        val today = LocalDate.now().toString()
        val mealPlanned = planningDao.isMealPlannedForType(today, type)

        if (!mealPlanned) {
            Log.d("Notification", "Aucun repas prévu pour type=$type → pas de notification")
            return
        }

        val settings = settingsDao.getSetting()
        val enabled = when (type) {
            1 -> settings!!.morningEnabled
            2 -> settings!!.noonEnabled
            3 -> settings!!.snackEnabled
            4 -> settings!!.eveningEnabled
            else -> false
        }

        if (!enabled) {
            Log.d("Notification", "Notifications désactivées pour type=$type")
            return
        }

        send(type)
    }
}
