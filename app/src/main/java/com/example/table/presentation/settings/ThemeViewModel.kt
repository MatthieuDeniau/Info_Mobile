package com.example.table.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val useCases: ThemeRepository
) : ViewModel() {

    private val _theme = MutableStateFlow(true)
    val theme: StateFlow<Boolean> = _theme.asStateFlow()

    init {
        loadTheme()
    }

    private fun loadTheme() {
        useCases.getTheme()
            .onEach { isTheme1 ->
                _theme.value = isTheme1
            }
            .launchIn(viewModelScope)
    }

    fun saveTheme(isTheme1: Boolean) {
        viewModelScope.launch {
            useCases.saveTheme(isTheme1)
        }
    }

    fun onThemeChange(bool: Boolean) { _theme.value = bool }
}