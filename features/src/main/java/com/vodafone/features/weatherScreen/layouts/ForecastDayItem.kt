package com.vodafone.features.weatherScreen.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vodafone.domain.models.forecast.ForecastDayModel
import okhttp3.internal.trimSubstring


@Composable
fun ForecastDayItem(day: ForecastDayModel) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(160.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = day.date)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${day.day.temp}°C")
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(model = "https://${day.day.condition.icon}", contentDescription = day.day.condition.text)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = day.day.condition.text)
        }
    }
}