package com.example.table.presentation.notification

import androidx.lifecycle.ViewModel
import com.example.table.data.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val useCases: NotificationRepository
) : ViewModel() {
    
    fun showSimpleNotification() {
        useCases.sendNotification(1) // Example ID
    }

    fun updateNotification() {
        // Logique de mise à jour si nécessaire via UseCase
        useCases.sendNotification(1) 
    }

    fun cancelNotification() {
        useCases.cancelNotification(1)
    }
}
