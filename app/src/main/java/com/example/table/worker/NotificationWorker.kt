package com.example.table.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.table.data.repository.NotificationRepository
import com.example.table.data.repository.SettingsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val notificationRepository: NotificationRepository,
    private val settingsRepository: SettingsRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val type = inputData.getInt(KEY_TYPE, -1)
        if (type !in 1..4) return Result.failure()

        val notificationManager = androidx.core.app.NotificationManagerCompat.from(applicationContext)

        notificationManager.cancelAll()

        notificationRepository.sendReminderNotification(type)

        settingsRepository.scheduleAllReminders.scheduleNextForType(type)

        return Result.success()
    }

    companion object {
        const val KEY_TYPE = "reminder_type"
    }
}
