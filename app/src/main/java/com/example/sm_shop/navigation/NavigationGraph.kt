package com.example.sm_shop.navigation

import com.example.accounts.ui.destinations.AccountScreenDestination
import com.example.home.ui.destinations.HomeScreenDestination
import com.example.home.ui.destinations.ProductScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavigationGraph {
    val home =
        object : NavGraphSpec {
            override val destinationsByRoute: Map<String, DestinationSpec<*>>
                get() =
                    listOf(
                        HomeScreenDestination
                    ).associateBy { it.route }

            override val route: String = "home"

            override val startRoute: Route
                get() = HomeScreenDestination
        }

    val product = object: NavGraphSpec{
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() =
                listOf(
                    ProductScreenDestination
                ).associateBy { it.route }

        override val route: String = "product"

        override val startRoute: Route
            get() = ProductScreenDestination
    }


    val account = object: NavGraphSpec{
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() =
                listOf(
                    AccountScreenDestination
                ).associateBy { it.route }

        override val route: String = "account"

        override val startRoute: Route
            get() = AccountScreenDestination
    }

    val root =
        object : NavGraphSpec {
            override val route = "root"

            override val startRoute: Route
                get() = home.startRoute

            override val destinationsByRoute = home.destinationsByRoute

            override val nestedNavGraphs =
                listOf(home, account, product)
        }
}