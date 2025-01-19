package com.vodafone.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.vodafone.core.theme.WeatherTheme
import com.vodafone.core.utils.SharedPreferencesHelper
import com.vodafone.weather.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(SharedPreferencesHelper(this))  // Entry screen for city input
                }
            }
        }
    }
}