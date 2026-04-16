package com.example.table.domain.usecases

import com.example.table.data.local.SettingsDao
import com.example.table.domain.model.SettingsEntity
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(
    private val settingsDao: SettingsDao
) {
    suspend operator fun invoke(settings: SettingsEntity) {
        settingsDao.saveSettings(settings)
    }
}