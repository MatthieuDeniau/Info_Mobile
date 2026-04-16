package com.example.table.data.repository

import com.example.table.domain.usecases.GetSettingsUseCase
import com.example.table.domain.usecases.SaveSettingsUseCase
import com.example.table.domain.usecases.ScheduleAllRemindersUseCase
import com.example.table.domain.usecases.SendNotificationUseCase
import javax.inject.Inject

data class SettingsRepository @Inject constructor(
    val getSettings: GetSettingsUseCase,
    val saveSettings: SaveSettingsUseCase,
    val sendNotification: SendNotificationUseCase,
    val scheduleAllReminders: ScheduleAllRemindersUseCase
)