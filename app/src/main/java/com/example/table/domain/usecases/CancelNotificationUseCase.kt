package com.example.table.domain.usecases

import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

class CancelNotificationUseCase @Inject constructor(
    private val notificationManager: NotificationManagerCompat
) {
    operator fun invoke(id: Int) {
        notificationManager.cancel(id)
    }
}
