package com.example.table.domain.usecases

import com.example.table.data.local.ThemeDao
import com.example.table.domain.model.ThemeEntity
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(
    private val themeDao: ThemeDao
) {
    suspend operator fun invoke(isTheme1: Boolean) {
        themeDao.saveTheme(ThemeEntity(theme = isTheme1))
    }
}