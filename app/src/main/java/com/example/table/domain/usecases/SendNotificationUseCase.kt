package com.example.table.domain.usecases

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.table.di.ReminderNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SendNotificationUseCase @Inject constructor(
    private val notificationManager: NotificationManagerCompat,
    @ReminderNotification private val reminderBuilder: NotificationCompat.Builder,
    @ApplicationContext private val context: Context
) {
    operator fun invoke(type: Int) {
        if (!hasNotificationPermission()) return

        val (title, message) = when (type) {
            1 -> "Petit-déjeuner" to "C'est l'heure du petit-déjeuner !"
            2 -> "Déjeuner" to "C'est l'heure de manger !"
            3 -> "Collation" to "Une petite collation ?"
            4 -> "Dîner" to "C'est l'heure du repas du soir."
            else -> return
        }

        val notification = reminderBuilder
            .setContentTitle(title)
            .setContentText(message)
            .build()

        try {
            notificationManager.notify(100 + type, notification)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
}
