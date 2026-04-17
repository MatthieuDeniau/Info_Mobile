package com.example.table.presentation.settings

import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.table.domain.model.SettingsEntity
import com.example.table.data.repository.SettingsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val useCases: SettingsRepository
) : ViewModel() {

    private val _allNotificationsEnabled = MutableStateFlow(true)
    val allNotificationsEnabled: StateFlow<Boolean> = _allNotificationsEnabled.asStateFlow()

    private val _morningEnabled = MutableStateFlow(true)
    val morningEnabled: StateFlow<Boolean> = _morningEnabled.asStateFlow()
    private val _morningTime = MutableStateFlow("08:00")
    val morningTime: StateFlow<String> = _morningTime.asStateFlow()

    private val _noonEnabled = MutableStateFlow(true)
    val noonEnabled: StateFlow<Boolean> = _noonEnabled.asStateFlow()
    private val _noonTime = MutableStateFlow("12:00")
    val noonTime: StateFlow<String> = _noonTime.asStateFlow()

    private val _snackEnabled = MutableStateFlow(true)
    val snackEnabled: StateFlow<Boolean> = _snackEnabled.asStateFlow()
    private val _snackTime = MutableStateFlow("16:00")
    val snackTime: StateFlow<String> = _snackTime.asStateFlow()

    private val _eveningEnabled = MutableStateFlow(true)
    val eveningEnabled: StateFlow<Boolean> = _eveningEnabled.asStateFlow()
    private val _eveningTime = MutableStateFlow("19:00")
    val eveningTime: StateFlow<String> = _eveningTime.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        useCases.getSettings()
            .onEach { settings ->
                if (settings != null) {
                    _allNotificationsEnabled.value = settings.allNotificationsEnabled

                    _morningEnabled.value = settings.morningEnabled
                    _morningTime.value = settings.morningTime

                    _noonEnabled.value = settings.noonEnabled
                    _noonTime.value = settings.noonTime

                    _snackEnabled.value = settings.snackEnabled
                    _snackTime.value = settings.snackTime

                    _eveningEnabled.value = settings.eveningEnabled
                    _eveningTime.value = settings.eveningTime
                }
            }
            .launchIn(viewModelScope)
    }

    fun saveSettings() {
        viewModelScope.launch {
            val settings = SettingsEntity(
                id = 0,
                allNotificationsEnabled = _allNotificationsEnabled.value,

                morningEnabled = _morningEnabled.value,
                morningTime = _morningTime.value,

                noonEnabled = _noonEnabled.value, noonTime = _noonTime.value,

                snackEnabled = _snackEnabled.value,
                snackTime = _snackTime.value,

                eveningEnabled = _eveningEnabled.value,
                eveningTime = _eveningTime.value,
            )

            useCases.saveSettings(settings)
            useCases.scheduleAllReminders()
            useCases.showSaveNotification()
        }
    }

    fun onAllNotificationsEnabledChange(value: Boolean) { _allNotificationsEnabled.value = value }
    fun onMorningEnabledChange(value: Boolean) { _morningEnabled.value = value }
    fun onMorningTimeChange(time: String) { _morningTime.value = time }
    fun onNoonEnabledChange(value: Boolean) { _noonEnabled.value = value }
    fun onNoonTimeChange(time: String) { _noonTime.value = time }
    fun onSnackEnabledChange(value: Boolean) { _snackEnabled.value = value }
    fun onSnackTimeChange(time: String) { _snackTime.value = time }
    fun onEveningEnabledChange(value: Boolean) { _eveningEnabled.value = value }
    fun onEveningTimeChange(time: String) { _eveningTime.value = time }
}
