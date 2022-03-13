package com.lelin.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lelin.weatherapp.screens.about.AboutScreen
import com.lelin.weatherapp.screens.favorities.FavoritesScreen
import com.lelin.weatherapp.screens.main.MainScreen
import com.lelin.weatherapp.screens.main.MainViewModel
import com.lelin.weatherapp.screens.search.SearchScreen
import com.lelin.weatherapp.screens.setting.SettingScreen
import com.lelin.weatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name ){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController)
        }
        composable(WeatherScreens.MainScreen.name+"/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                }
            ) ){ navBack ->
            navBack.arguments?.getString("city").let { city->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel = mainViewModel,city = city )

            }

        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.FavoriteScreen.name){
            FavoritesScreen(navController = navController)
        }

        composable(WeatherScreens.SettingScreen.name){
            SettingScreen(navController = navController)
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

    }
    
}