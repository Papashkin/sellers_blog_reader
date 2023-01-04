package com.antsfamily.sellersblogreader.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antsfamily.sellersblogreader.ui.home.HomeScreen
import com.antsfamily.sellersblogreader.ui.post.PostScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = {
            print(it.toString())
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(
                    Screen.Post.route,
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { entry ->
                    PostScreen(
                        navController = navController,
                        id = entry.arguments?.getInt("id") ?: 0
                    )
                }
            }
        }
    )
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Post : Screen("post/{id}")
}
