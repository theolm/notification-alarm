package dev.theolm.pushalarm.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.theolm.pushalarm.ui.navigation.AddAlarmRoute
import dev.theolm.pushalarm.ui.navigation.HomeRoute
import dev.theolm.pushalarm.ui.navigation.LocalNavigator
import dev.theolm.pushalarm.ui.screens.add.AddAlarmScreen
import dev.theolm.pushalarm.ui.screens.home.HomeScreen
import dev.theolm.pushalarm.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme(isDynamicColorEnabled = true) {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalNavigator provides navController
                ) {
                    NavHost(navController = navController, startDestination = HomeRoute) {
                        composable<HomeRoute> {
                            HomeScreen()
                        }

                        composable<AddAlarmRoute> {
                            AddAlarmScreen()
                        }
                    }
                }
            }
        }
    }

    private fun NavHostController.popOrCloseActivity(activity: Activity) {
        if (!popBackStack()) {
            activity.finish()
        }
    }
}
