package com.example.table.presentation.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.blanc
import com.example.table.gris
import com.example.table.noir
import java.time.LocalTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerSetting(
    label: String,
    time: String,
    onTimeSelected: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentHour by remember { mutableStateOf(0) }
    var currentMinute by remember { mutableStateOf(0) }

    fun updateCurrentTime() {
        val now = java.time.LocalTime.now()
        currentHour = now.hour
        currentMinute = now.minute
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = noir,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedButton(
            onClick = {
                updateCurrentTime()
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = blanc,
                contentColor = noir
            ),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text(
                text = time.ifEmpty { "Sélectionner une heure" },
                fontSize = 16.sp
            )
        }
    }

    if (showDialog) {
        val timePickerState = rememberTimePickerState(
            initialHour = currentHour,
            initialMinute = currentMinute,
            is24Hour = true
        )

        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = blanc,
            confirmButton = {
                TextButton(
                    onClick = {
                        val formattedTime = String.format(
                            Locale.getDefault(),
                            "%02d:%02d",
                            timePickerState.hour,
                            timePickerState.minute
                        )
                        onTimeSelected(formattedTime)
                        showDialog = false
                    }
                ) {
                    Text("OK", color = noir)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Annuler", color = noir)
                }
            },
            text = {
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = gris,
                        clockDialSelectedContentColor = blanc,
                        clockDialUnselectedContentColor = noir,
                        selectorColor = noir,
                        periodSelectorBorderColor = noir,
                        periodSelectorSelectedContainerColor = noir,
                        periodSelectorUnselectedContainerColor = blanc,
                        periodSelectorSelectedContentColor = blanc,
                        periodSelectorUnselectedContentColor = noir,
                        timeSelectorSelectedContainerColor = noir,
                        timeSelectorUnselectedContainerColor = gris,
                        timeSelectorSelectedContentColor = blanc,
                        timeSelectorUnselectedContentColor = noir
                    )
                )
            }
        )
    }
}