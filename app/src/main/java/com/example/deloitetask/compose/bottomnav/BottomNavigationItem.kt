package com.example.deloitetask.compose.bottomnav

import com.example.deloitetask.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home_black_24dp,"home")
    object DashBoard: BottomNavItem("My Network",R.drawable.ic_dashboard_black_24dp,"dash_board")
}

sealed class  Screen(val route:String){
    object MovieDetailsScreen:Screen("movie_details_screen")
}