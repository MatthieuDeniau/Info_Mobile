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
fun TitleSwitchSetting(
    label: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            color = colorScheme.tertiary,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = value,
            onCheckedChange = onValueChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorScheme.primary,
                checkedTrackColor = colorScheme.tertiary,
                uncheckedThumbColor = colorScheme.tertiary,
                uncheckedTrackColor = colorScheme.secondary,
                uncheckedBorderColor = colorScheme.tertiary
            )
        )
    }
}