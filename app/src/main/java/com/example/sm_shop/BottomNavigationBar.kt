package com.example.sm_shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.accounts.ui.destinations.AccountScreenDestination
import com.example.home.ui.destinations.HomeScreenDestination
import com.example.home.ui.destinations.ProductScreenDestination
import com.example.sm_shop.data.BottomNavigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi


val items: List<BottomNavigation> = listOf(
    BottomNavigation(
        title = "Favourite",
        icon = Icons.Rounded.Favorite,
        index = 0
    ),
    BottomNavigation(
        title = "Home",
        icon = Icons.Rounded.Home,
        index = 1
    ),
    BottomNavigation(
        title = "Account",
        icon = Icons.Rounded.AccountCircle,
        index = 2
    ),
)


@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun BottomNavigationBar(navigator: NavHostController) {
    var activeTab = 0
    NavigationBar {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == activeTab,
                    onClick = {
                        when (index) {
                            0 -> {
                                navigator.navigate(HomeScreenDestination().route)
                                activeTab = 0
                            }


                            1 -> {
                                navigator.navigate(ProductScreenDestination().route)
                                activeTab = 1
                            }

                            2 -> {
                                navigator.navigate(AccountScreenDestination().route)
                                activeTab = 2
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon, // Corrected icon set
                            contentDescription = "Account", // Replace with your actual content description
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}