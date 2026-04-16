package com.example.table.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNotification

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ReminderNotification

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StartNotification


@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ) : NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val channel = NotificationChannel(
                "Main channel ID",
                "Main channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val reminderChannel = NotificationChannel(
                "reminder_channel",
                "Rappels",
                NotificationManager.IMPORTANCE_HIGH
            )
            val startChannel = NotificationChannel(
                "Start Channel",
                "Start Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(reminderChannel)
            notificationManager.createNotificationChannel(startChannel)
        }

        return notificationManager
    }

    @MainNotification
    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ) : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "Main channel ID")
            .setContentTitle("Welcome")
            .setContentText("Plannin App")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    @ReminderNotification
    @Singleton
    @Provides
    fun provideReminderNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "reminder_channel")
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    @StartNotification
    @Singleton
    @Provides
    fun provideStartNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "Start Channel")
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }
}
