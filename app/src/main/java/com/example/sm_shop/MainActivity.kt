package com.example.sm_shop


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.accounts.ui.AccountScreen
import com.example.compose.SmshopTheme
import com.example.home.ui.HomeScreen
import com.example.sm_shop.navigation.CoreFeatureNavigatorSettings
import com.example.sm_shop.navigation.NavigationGraph
import com.example.sm_shop.navigation.currentNavigator
//import com.example.sm_shop.navigation.NavigationGraph
import com.example.sm_shop.viewModel.MainViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }

        setContent {
            SmshopTheme {

                SetBarColor(color = MaterialTheme.colorScheme.background)
                // A surface container using the 'background' color from the theme


                val engine =
                    rememberAnimatedNavHostEngine(
                        navHostContentAlignment = Alignment.TopCenter,
                        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
                        defaultAnimationsForNestedNavGraph =
                        mapOf(
                            NavigationGraph.home to
                                    NestedNavGraphDefaultAnimations(
                                        enterTransition = { fadeIn(animationSpec = tween(2000)) },
                                        exitTransition = { fadeOut(animationSpec = tween(2000)) },
                                    ),
                        ),
                    )

                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navigationController = engine.rememberNavController(bottomSheetNavigator)



                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navigator = navigationController)
                        }) { padding ->



                        ModalBottomSheetLayout(
                            bottomSheetNavigator = bottomSheetNavigator,
                            sheetShape =
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                            ),
                        ) {
                            DestinationsNavHost(
                                navController = navigationController,
                                navGraph = NavigationGraph.root,
                                modifier = Modifier.padding(paddingValues = padding),
                                dependenciesContainerBuilder = {
                                    dependency(currentNavigator())
                                },
                            )
                        }

                    }
                }

            }
        }
    }
}


@Composable
fun SetBarColor(color: androidx.compose.ui.graphics.Color) {
    val sysUiController = rememberSystemUiController()

    SideEffect {
        sysUiController.setSystemBarsColor(color = color)
    }
}





