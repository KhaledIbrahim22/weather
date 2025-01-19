package com.vodafone.features.weatherScreen.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vodafone.domain.models.forecast.ForecastDayModel

@Composable
fun ForecastSection(forecastList: List<ForecastDayModel>) {
    Column {
        Text(text = "7-Day Forecast", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(forecastList.size) { day ->
                ForecastDayItem(forecastList[day])
            }
        }
    }
}