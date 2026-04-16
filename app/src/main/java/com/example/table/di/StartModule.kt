package com.example.table.di

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.table.data.local.MealDao
import com.example.table.data.local.SettingsDao
import com.example.table.data.repository.StartRepository
import com.example.table.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StartModule {
    @Provides
    @Singleton
    fun provideStartUseCases(
        mealDao: MealDao,
        settingsDao: SettingsDao,
        notificationManager: NotificationManagerCompat,
        @StartNotification startBuilder: NotificationCompat.Builder,
        @ApplicationContext context: Context
    ): StartRepository {
        return StartRepository(
            getLastMealDate = GetLastMealDateUseCase(mealDao),
            sendStartNotification = SendStartNotificationUseCase(
                notificationManager,
                startBuilder,
                context
            ),
            scheduleAllReminders = ScheduleAllRemindersUseCase(context, settingsDao)
        )
    }
}
