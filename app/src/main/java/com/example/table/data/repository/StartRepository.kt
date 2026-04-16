package com.example.table.data.repository

import com.example.table.domain.usecases.GetLastMealDateUseCase
import com.example.table.domain.usecases.ScheduleAllRemindersUseCase
import com.example.table.domain.usecases.SendStartNotificationUseCase
import javax.inject.Inject

data class StartRepository @Inject constructor(
    val getLastMealDate: GetLastMealDateUseCase,
    val sendStartNotification: SendStartNotificationUseCase,
    val scheduleAllReminders: ScheduleAllRemindersUseCase
)