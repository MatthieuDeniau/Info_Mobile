package com.example.table.presentation.generalComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.table.blanc
import com.example.table.gris
import com.example.table.navigation.Screen
import com.example.table.noir

@Composable
fun MenuItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) noir else gris.copy(red = 165/250f, green = 165/250f, blue = 165/250f)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp
            ),
            color = color
        )
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(blanc)
            .padding(top = 16.dp, bottom = 0.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MenuItem(
            icon = Icons.Outlined.Event,//Icons.Outlined.CalendarMonth
            label = "Planning",
            isSelected = currentRoute == Screen.PlanningScreen.route
        ) {
            if (currentRoute != Screen.PlanningScreen.route) {
                navController.navigate(Screen.PlanningScreen.route)
            }
        }

        /*MenuItem(
            icon = Icons.Outlined.ShoppingCart,
            label = "NI",
            isSelected = currentRoute == ""
        ) {
            if (currentRoute != "") {
                navController.navigate("NI")
            }
        }*/

        MenuItem(
            icon = Icons.AutoMirrored.Default.MenuBook,
            label = "Recettes",
            isSelected = currentRoute == Screen.RecipeListScreen.route
        ) {
            if (currentRoute != Screen.RecipeListScreen.route) {
                navController.navigate(Screen.RecipeListScreen.route)
            }
        }

        MenuItem(
            icon = Icons.Outlined.Settings,
            label = "Paramètres",
            isSelected = currentRoute == Screen.SettingScreen.route
        ) {
            if (currentRoute != Screen.SettingScreen.route) {
                navController.navigate(Screen.SettingScreen.route)
            }
        }
    }
}
