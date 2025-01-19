package com.vodafone.features.weatherScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vodafone.core.network.State
import com.vodafone.core.utils.composable.showToast
import com.vodafone.core.utils.constants.NavigationRouts.INPUT_SCREEN
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import com.vodafone.features.weatherScreen.layouts.CurrentWeatherSection
import com.vodafone.features.weatherScreen.layouts.ForecastSection
import com.vodafone.features.weatherScreen.viewModels.CurrentWeatherViewModel
import com.vodafone.features.weatherScreen.viewModels.ForecastIntent
import com.vodafone.features.weatherScreen.viewModels.ForecastViewModel

@Composable
fun WeatherScreen(navController: NavController) {
    val currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val forecastViewModel: ForecastViewModel = hiltViewModel()
    val currentWeatherState by currentWeatherViewModel.state.observeAsState()
    val forecastState by forecastViewModel.state.collectAsState()

    // Fetch data when the screen is launched
    LaunchedEffect(Unit) {
        val city: String = currentWeatherViewModel.preferences.getSearchedCity() ?: ""
        currentWeatherViewModel.getCurrentWeather(cityName = city)
        forecastViewModel.processIntent(ForecastIntent.GetForecastIntent(cityName = city))
    }

    // Handle back button press
    BackHandler {
        // Replace WeatherScreen with CityInputScreen
        navController.navigate(INPUT_SCREEN) {
            popUpTo(INPUT_SCREEN) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_search),
                contentDescription = "Search another"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Current Weather Section (MVVM)
        when (currentWeatherState) {
            is State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }

            is State.Success -> {
                CurrentWeatherSection((currentWeatherState as State.Success<CurrentWeatherResponseModel>).data)
            }

            is State.Error -> {
                val errorMessage = (currentWeatherState as State.Error).message ?: "An error occurred"
                showToast(LocalContext.current, errorMessage)
                navController.navigate(INPUT_SCREEN) {
                    popUpTo(INPUT_SCREEN) { inclusive = true }
                }
            }

            else -> {}
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 7-Day Forecast Section (MVI)
        when (forecastState) {
            is State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }

            is State.Success -> {
                ForecastSection((forecastState as State.Success).data.forecast.forecastDay)
            }

            is State.Error -> {
                val errorMessage = (forecastState as State.Error).message ?: "An error occurred"
                showToast(LocalContext.current, errorMessage)
                navController.navigate(INPUT_SCREEN) {
                    popUpTo(INPUT_SCREEN) { inclusive = true }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Fetch Another City Button
        Button(
            onClick = {
                // Replace WeatherScreen with CityInputScreen
                navController.navigate(INPUT_SCREEN) {
                    popUpTo(INPUT_SCREEN) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fetch Another City")
        }
    }
}
