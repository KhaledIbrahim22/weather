package com.vodafone.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vodafone.core.utils.SharedPreferencesHelper
import com.vodafone.core.utils.constants.NavigationRouts.INPUT_SCREEN
import com.vodafone.core.utils.constants.NavigationRouts.WEATHER_SCREEN
import com.vodafone.features.cityInputScreen.CityInputScreen
import com.vodafone.features.weatherScreen.WeatherScreen

@Composable
fun AppNavigation(preferences: SharedPreferencesHelper) {
    val navController = rememberNavController()
    val startDestination = if (preferences.getSearchedCity() != null) {
        WEATHER_SCREEN
    } else {
        INPUT_SCREEN
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(INPUT_SCREEN) {
            CityInputScreen(navController)
        }
        composable(WEATHER_SCREEN) {
            WeatherScreen(navController)
        }
    }
}