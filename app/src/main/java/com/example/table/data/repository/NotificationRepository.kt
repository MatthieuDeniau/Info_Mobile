package com.example.table.data.repository

import com.example.table.domain.usecases.CancelNotificationUseCase
import com.example.table.domain.usecases.SendNotificationUseCase
import com.example.table.domain.usecases.SendReminderNotificationUseCase
import javax.inject.Inject

data class NotificationRepository @Inject constructor(
    val sendNotification: SendNotificationUseCase,
    val cancelNotification: CancelNotificationUseCase,
    val sendReminderNotification: SendReminderNotificationUseCase
)
