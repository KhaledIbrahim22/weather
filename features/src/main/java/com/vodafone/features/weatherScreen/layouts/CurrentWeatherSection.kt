package com.vodafone.features.weatherScreen.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import okhttp3.internal.trimSubstring


@Composable
fun CurrentWeatherSection(weather: CurrentWeatherResponseModel) {
    Column {
        Text(text = "Current Weather", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${weather.location.country}, ${weather.location.region}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Temperature: ${weather.current.temp}°C")
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = "Condition: ${weather.current.condition.text}")
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(model = "https://${weather.current.condition.icon}", contentDescription = weather.current.condition.text)

        }
    }
}
