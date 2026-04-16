package com.example.table.presentation.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.noir

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        color = noir,
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
