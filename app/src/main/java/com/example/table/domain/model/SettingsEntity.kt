package com.example.table.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 0,

    val allNotificationsEnabled: Boolean,

    val morningEnabled: Boolean,
    val morningTime: String,

    val noonEnabled: Boolean,
    val noonTime: String,

    val snackEnabled: Boolean,
    val snackTime: String,

    val eveningEnabled: Boolean,
    val eveningTime: String
)
