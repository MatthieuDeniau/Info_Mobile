package com.example.table.domain.usecases

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.table.di.StartNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SendStartNotificationUseCase @Inject constructor(
    private val notificationManager: NotificationManagerCompat,
    @StartNotification private val startBuilder: NotificationCompat.Builder,
    @ApplicationContext private val context: Context
) {
    operator fun invoke(days: Long) {
        if (!hasNotificationPermission()) return

        val notification = if (days == -1L) {
            startBuilder
                .setContentTitle("Aucun repas planifié")
                .setContentText("Tu n'as rien planifié depuis toujours")
                .build()
        } else {
            startBuilder
                .setContentTitle("Aucun repas planifié")
                .setContentText("Tu n'as rien planifié depuis $days jours")
                .build()
        }

        try {
            notificationManager.notify(999, notification)
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
