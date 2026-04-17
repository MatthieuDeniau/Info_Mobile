package com.example.table.data.repository

import com.example.table.domain.usecases.GetThemeUseCase
import com.example.table.domain.usecases.SaveThemeUseCase
import javax.inject.Inject

data class ThemeRepository @Inject constructor(
    val getTheme: GetThemeUseCase,
    val saveTheme: SaveThemeUseCase
)