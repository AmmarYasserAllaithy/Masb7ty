package com.ammaryasser.masb7ty

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.ammaryasser.masb7ty.screen.AboutScreen
import com.ammaryasser.masb7ty.screen.Masba7tyScreen
import com.ammaryasser.masb7ty.screen.Tasabee7Screen
import com.ammaryasser.masb7ty.util.Screen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Masba7ty.routeOfTasbee7Id(0)
    ) {
        composable(
            route = Screen.Masba7ty.fullRoute(),
            arguments = listOf(
                navArgument(Screen.Masba7ty.ID_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                },
            ),
        ) {
            Masba7tyScreen(it.arguments?.getInt(Screen.Masba7ty.ID_KEY)) { isClear ->
                navController.navigate(Screen.Tasabee7.route) {
                    if (isClear) popUpTo(Screen.Masba7ty.fullRoute()) {
                        inclusive = true
                        saveState = true
                    }
                }
            }
        }

        composable(route = Screen.Tasabee7.route) {
            Tasabee7Screen(
                onNavToMasba7tyScreen = { id ->
                    navController.navigate(Screen.Masba7ty.routeOfTasbee7Id(id)) {
                        //launchSingleTop = true
                        popUpTo(Screen.Tasabee7.route) {
                            inclusive = true
                            saveState = true
                        }
                    }
                },
                onNavToAboutScreen = {
                    navController.navigate(Screen.About.route)
                },
                onNavBack = {
                    navController.popBackStack()
                })
        }

        composable(route = Screen.About.route) {
            AboutScreen {
                navController.popBackStack()
            }
        }
    }
}