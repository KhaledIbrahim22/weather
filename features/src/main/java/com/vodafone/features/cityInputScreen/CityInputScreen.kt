package com.vodafone.features.cityInputScreen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vodafone.core.network.NetworkUtils
import com.vodafone.core.network.State
import com.vodafone.core.utils.composable.showToast
import com.vodafone.core.utils.constants.NavigationRouts
import com.vodafone.features.cityInputScreen.viewModels.CityInputViewModel

@Composable
fun CityInputScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: CityInputViewModel = hiltViewModel()
    var city by remember { mutableStateOf("") }
    val getState: State<String?>? by viewModel.getState.observeAsState()
    val setState: State<Unit>? by viewModel.setState.observeAsState()
    var shouldNavigate by remember { mutableStateOf(true) } // Flag to control navigation

    LaunchedEffect(getState) {
        if (shouldNavigate) { // Only navigate if the flag is true
            when (getState) {
                is State.Success -> {
                    if ((getState as State.Success<String?>).data != null)
                        navController.navigate(NavigationRouts.WEATHER_SCREEN)
                }

                else -> {}
            }
        }
    }

    LaunchedEffect(setState) {
        if (shouldNavigate) { // Only navigate if the flag is true
            when (setState) {
                is State.Success -> {
                    navController.navigate(NavigationRouts.WEATHER_SCREEN)
                }

                else -> {}
            }
        }
    }

    // Handle back button press
    BackHandler {
        // Exit the app
        (context as? Activity)?.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // City Input Field
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City Name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fetch Weather Button
        Button(
            onClick = {
                if (NetworkUtils(context).isInternetAvailable()) {
                    shouldNavigate = true // Allow navigation after fetching
                    viewModel.setCityName(city)
                } else {
                    showToast(context, "You are not connected to the internet.")
                }
            },
            enabled = getState !is State.Loading
        ) {
            Text("Fetch Weather")
        }
    }

    // Reset the flag when navigating back to CityInputScreen
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route == NavigationRouts.INPUT_SCREEN) {
                shouldNavigate = false // Disable navigation when returning
            }
        }
    }
}