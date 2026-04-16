package com.example.table.di

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.table.data.local.SettingsDao
import com.example.table.data.repository.SettingsRepository
import com.example.table.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideSettingsUseCases(
        settingsDao: SettingsDao,
        notificationManager: NotificationManagerCompat,
        @ReminderNotification reminderBuilder: NotificationCompat.Builder,
        @ApplicationContext context: Context
    ): SettingsRepository {
        return SettingsRepository(
            getSettings = GetSettingsUseCase(settingsDao),
            saveSettings = SaveSettingsUseCase(settingsDao),
            sendNotification = SendNotificationUseCase(
                notificationManager,
                reminderBuilder,
                context
            ),
            scheduleAllReminders = ScheduleAllRemindersUseCase(context, settingsDao)
        )
    }
}
