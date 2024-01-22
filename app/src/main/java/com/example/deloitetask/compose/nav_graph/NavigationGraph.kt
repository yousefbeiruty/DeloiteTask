package com.example.deloitetask.compose.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deloitetask.compose.bottomnav.BottomNavItem
import com.example.deloitetask.compose.bottomnav.Screen
import com.example.deloitetask.compose.screens.DashBoardScreen
import com.example.deloitetask.compose.screens.DetailsScreen
import com.example.deloitetask.compose.screens.home.HomeScreen
import com.example.deloitetask.compose.sharViewModel.SharedViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            sharedViewModel.visibleBottom(true)
            HomeScreen(navController,sharedViewModel=sharedViewModel)
        }
        composable(BottomNavItem.DashBoard.screen_route) {
            sharedViewModel.visibleBottom(true)
            DashBoardScreen(navController)
        }
        composable(Screen.MovieDetailsScreen.route) {
          sharedViewModel.visibleBottom(false)
          DetailsScreen(sharedViewModel)
        }

    }
}
