package com.example.table.presentation.planning.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.table.blanc
import com.example.table.gris
import com.example.table.noir

@Composable
fun TopBar(
    label: String,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: (() -> Unit)? = null,
    onRightClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gris)
            .statusBarsPadding()
            .padding(horizontal = 30.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (leftIcon != null && onLeftClick != null) {
            IconButton(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .background(
                        color = blanc,
                        shape = RoundedCornerShape(40.dp)
                    ),
                onClick = onLeftClick
            ) {
                Icon(
                    imageVector = leftIcon,
                    contentDescription = "Left action",
                    tint = noir
                )
            }
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }

        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 24.sp),
            color = noir
        )

        if (rightIcon != null && onRightClick != null) {
            IconButton(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .background(
                        color = blanc,
                        shape = RoundedCornerShape(40.dp)
                    ),
                onClick = onRightClick
            ) {
                Icon(
                    imageVector = rightIcon,
                    contentDescription = "Right action",
                    tint = noir
                )
            }
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}