package com.example.table.domain.usecases

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.table.data.local.SettingsDao
import com.example.table.worker.NotificationWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ScheduleAllRemindersUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val settingsDao: SettingsDao
) {
    private val workManager = WorkManager.getInstance(context)

    suspend operator fun invoke() {
        val settings = settingsDao.getSetting() ?: return

        if (!settings.allNotificationsEnabled) return

        val now = LocalDateTime.now()

        if (settings.morningEnabled) {
            val (h, m) = parseTime(settings.morningTime)
            val target = now.withHour(h).withMinute(m).withSecond(0).withNano(0)

            if (!target.isBefore(now)) {
                Log.d("ScheduleTest", "Worker programmé pour type=1 à $h:$m")
                scheduleReminder(1, h, m)
            } else {
                Log.d("ScheduleTest", "Heure déjà passée pour type=1 → ignoré")
            }
        }

        if (settings.noonEnabled) {
            val (h, m) = parseTime(settings.noonTime)
            val target = now.withHour(h).withMinute(m).withSecond(0).withNano(0)

            if (!target.isBefore(now)) {
                Log.d("ScheduleTest", "Worker programmé pour type=2 à $h:$m")
                scheduleReminder(2, h, m)
            } else {
                Log.d("ScheduleTest", "Heure déjà passée pour type=2 → ignoré")
            }
        }

        if (settings.snackEnabled) {
            val (h, m) = parseTime(settings.snackTime)
            val target = now.withHour(h).withMinute(m).withSecond(0).withNano(0)

            if (!target.isBefore(now)) {
                Log.d("ScheduleTest", "Worker programmé pour type=3 à $h:$m")
                scheduleReminder(3, h, m)
            } else {
                Log.d("ScheduleTest", "Heure déjà passée pour type=3 → ignoré")
            }
        }

        if (settings.eveningEnabled) {
            val (h, m) = parseTime(settings.eveningTime)
            val target = now.withHour(h).withMinute(m).withSecond(0).withNano(0)

            if (!target.isBefore(now)) {
                Log.d("ScheduleTest", "Worker programmé pour type=4 à $h:$m")
                scheduleReminder(4, h, m)
            } else {
                Log.d("ScheduleTest", "Heure déjà passée pour type=4 → ignoré")
            }
        }
    }


    private fun scheduleReminder(type: Int, hour: Int, minute: Int) {
        val delay = calculateDelay(hour, minute)

        val data = workDataOf(
            NotificationWorker.KEY_TYPE to type
        )

        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniqueWork(
            "reminder_$type",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    private fun calculateDelay(hour: Int, minute: Int): Long {
        val now = LocalDateTime.now()
        var target = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)

        if (target.isBefore(now)) {
            target = target.plusDays(1)
        }

        return Duration.between(now, target).toMillis()
    }

    private fun parseTime(time: String): Pair<Int, Int> {
        val parts = time.split(":")
        return parts[0].toInt() to parts[1].toInt()
    }

    suspend fun scheduleNextForType(type: Int) {
        val settings = settingsDao.getSetting()

        val (hour, minute) = when (type) {
            1 -> settings!!.morningTime
            2 -> settings!!.noonTime
            3 -> settings!!.snackTime
            4 -> settings!!.eveningTime
            else -> return
        }.split(":").map { it.toInt() }

        val now = LocalDateTime.now()
        val demain = now.plusDays(1)
            .withHour(hour)
            .withMinute(minute)
            .withSecond(0)
            .withNano(0)

        val delay = Duration.between(now, demain).toMillis()

        Log.d("ScheduleTest", "Worker programmé pour type=$type à $hour:$minute")

        workManager.enqueueUniqueWork(
            "reminder_$type",
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(workDataOf(NotificationWorker.KEY_TYPE to type))
                .build()
        )
    }
}
