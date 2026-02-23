package com.example.table

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.table.presentation.ListMealScreen
import com.example.table.ui.theme.ÀTableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ÀTableTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color(red = 250, blue = 112, green = 165),
                        ),
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                ) { innerPadding ->
                    ListMealScreen(innerPadding)
                }
            }
        }
    }
}