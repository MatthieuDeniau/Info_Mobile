package com.example.table.domain.usecases

import com.example.table.data.local.ThemeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val themeDao: ThemeDao
) {
    operator fun invoke(): Flow<Boolean> {
        return themeDao.getTheme().map { it?.theme ?: true }
    }
}