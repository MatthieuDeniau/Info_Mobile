package com.example.table.domain.usecases

import com.example.table.data.local.SettingsDao
import com.example.table.domain.model.SettingsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsDao: SettingsDao
) {
    operator fun invoke(): Flow<SettingsEntity?> {
        return settingsDao.getSettings()
    }
}