package com.example.table.presentation.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SwitchSetting(
    label: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyLarge
        )
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.tertiary,
                uncheckedThumbColor = MaterialTheme.colorScheme.tertiary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondary,
                uncheckedBorderColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}
