package com.lelin.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lelin.weatherapp.screens.main.MainScreen
import com.lelin.weatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name ){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController)
        }
        composable(WeatherScreens.MainScreen.name){
            MainScreen(navController = navController)
        }
    }
    
}