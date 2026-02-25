package com.example.table.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.table.navigation.Screen
import com.example.table.presentation.components.MealCard

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel
) {
    Scaffold (
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(
                    Color(red = 250, blue = 112, green = 165),
                )
        ) {
            Box {
                Text(
                    text = "Planificateur",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(red = 156, blue = 44, green = 98),
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 36.sp,
                        textAlign = TextAlign.Left
                    )
                )
                IconButton(
                    onClick = {
                        navController.navigate(
                            Screen.MealListScreen.route
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Black,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Box (
                    modifier = Modifier
                        .background(
                            Color(red = 156, blue = 44, green = 98),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Planifier un repas",
                        style = TextStyle(
                            fontSize = 36.sp,
                            textAlign = TextAlign.Left
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box (
                    modifier = Modifier
                        .background(
                            Color(red = 156, blue = 44, green = 98),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Créer une recette",
                        style = TextStyle(
                            fontSize = 36.sp,
                            textAlign = TextAlign.Left
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box (
                    modifier = Modifier
                        .background(
                            Color(red = 156, blue = 44, green = 98),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Modifier une recette",
                        style = TextStyle(
                            fontSize = 36.sp,
                            textAlign = TextAlign.Left
                        )
                    )
                }
            }
        }
    }
}