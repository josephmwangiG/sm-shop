package com.example.home.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopTabEnum(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
) {
    Shoes(
        selectedIcon = Icons.Rounded.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        title = "SHOES"
    ),

    Bags(
        selectedIcon = Icons.Rounded.Refresh,
        unselectedIcon = Icons.Outlined.Refresh,
        title = "BAGS"
    ),
    Watches(
        selectedIcon = Icons.Rounded.Close,
        unselectedIcon = Icons.Outlined.Close,
        title = "WATCHES"
    ),

    Fashion(
        selectedIcon = Icons.Rounded.Refresh,
        unselectedIcon = Icons.Outlined.Refresh,
        title = "FASHION"
    ),
    Phones(
        selectedIcon = Icons.Rounded.Close,
        unselectedIcon = Icons.Outlined.Close,
        title = "PHONES"
    ),

    Gifts(
        selectedIcon = Icons.Rounded.Refresh,
        unselectedIcon = Icons.Outlined.Refresh,
        title = "GIFTS"
    ),
    Others(
        selectedIcon = Icons.Rounded.Close,
        unselectedIcon = Icons.Outlined.Close,
        title = "OTHERS"
    )
}
