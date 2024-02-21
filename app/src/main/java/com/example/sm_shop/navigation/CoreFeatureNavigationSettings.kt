package com.example.sm_shop.navigation

import androidx.navigation.NavController
import com.example.accounts.navigation.AccountModuleNavigation
import com.example.accounts.ui.destinations.AccountScreenDestination
import com.example.home.navigation.HomeModuleNavigation
import com.example.home.ui.destinations.HomeScreenDestination
import com.example.home.ui.destinations.ProductScreenDestination
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import javax.inject.Inject

class CoreFeatureNavigatorSettings @Inject constructor(private val navController: NavController) :
    AccountModuleNavigation, HomeModuleNavigation {
    override fun navigateToHomeScreen() =
        navController.navigate(HomeScreenDestination.route)

    override fun navigateToAccountScreen() =
        navController.navigate(AccountScreenDestination.route)

    override fun navigateToProductScreen() {
        navController.navigate(ProductScreenDestination.route)
    }

}

fun DependenciesContainerBuilder<*>.currentNavigator(): CoreFeatureNavigatorSettings {
    return CoreFeatureNavigatorSettings(navController = navController)
}