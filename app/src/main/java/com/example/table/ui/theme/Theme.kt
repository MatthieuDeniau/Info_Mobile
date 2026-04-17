package com.example.table.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.table.data.repository.ThemeRepository
import com.example.table.presentation.settings.ThemeViewModel

private val LightColorScheme2 = lightColorScheme(
    primary = Color(250, 165, 112),
    secondary = Color(156, 98, 44),
    tertiary = Color(0, 0, 0)
)

private val DarkColorScheme2 = darkColorScheme(
    primary = Color(40, 20, 15),
    secondary = Color(90, 55, 30),
    tertiary = Color(255, 200, 150)
)

private val LightColorScheme1 = lightColorScheme(
    primary = Color(250, 250, 250),
    secondary = Color(240, 240, 240),
    tertiary = Color(0, 0, 0)
)

private val DarkColorScheme1 = darkColorScheme(
    primary = Color(20, 20, 20),
    secondary = Color(40, 40, 40),
    tertiary = Color(220, 220, 220)
)

@Composable
fun ATableTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    //dynamicColor: Boolean = false,
    viewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val isBrownTheme by viewModel.theme.collectAsState()

    val colorScheme =
        when {
            /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            }*/

            isBrownTheme && darkTheme -> DarkColorScheme2
            isBrownTheme && !darkTheme -> LightColorScheme2
            !isBrownTheme && darkTheme -> DarkColorScheme1
            else -> LightColorScheme1
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
