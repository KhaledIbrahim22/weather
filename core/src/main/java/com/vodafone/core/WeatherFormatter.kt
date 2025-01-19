package com.vodafone.core

import java.text.SimpleDateFormat
import java.util.*

object WeatherFormatter {

    fun formatTemperature(tempInKelvin: Double): String {
        val tempInCelsius = tempInKelvin - 273.15
        return String.format("%.2f °C", tempInCelsius)
    }

    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp * 1000))
    }
}