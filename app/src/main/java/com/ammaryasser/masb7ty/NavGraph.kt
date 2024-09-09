package com.ammaryasser.masb7ty

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ammaryasser.masb7ty.screen.AboutScreen
import com.ammaryasser.masb7ty.screen.Masba7tyScreen
import com.ammaryasser.masb7ty.screen.Tasabee7Screen
import com.ammaryasser.masb7ty.util.Screens
import com.ammaryasser.masb7ty.util.ScreensNavArgs.ID_KEY


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Masba7ty(0).route
    ) {
        composable(
            route = Screens.Masba7ty().route,
            arguments = listOf(navArgument(ID_KEY) { type = NavType.IntType }),
        ) {
            Masba7tyScreen(zekrId = it.arguments?.getInt(ID_KEY) ?: 0) { isClear ->
                if (isClear) navController.popBackStack()
                navController.navigate(Screens.Tasabee7.route)
            }
        }

        composable(route = Screens.Tasabee7.route) {
            Tasabee7Screen(
                onNavToMasba7tyScreen = { zekrId ->
                    navController.popBackStack()
                    navController.navigate(Screens.Masba7ty(zekrId).route) {
                        launchSingleTop = true
                    }
                },
                onNavToAboutScreen = {
                    navController.navigate(Screens.About.route)
                },
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screens.About.route) {
            AboutScreen {
                navController.popBackStack()
            }
        }
    }

}
