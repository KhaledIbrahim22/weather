package com.vodafone.core

import org.junit.Assert.*
import org.junit.Test

class WeatherFormatterTest {

    @Test
    fun `formatTemperature converts Kelvin to Celsius correctly`() {
        val tempInKelvin = 300.0
        val formattedTemp = WeatherFormatter.formatTemperature(tempInKelvin)
        assertEquals("26.85 °C", formattedTemp)
    }

    @Test
    fun `formatDate converts timestamp to correct date format`() {
        val timestamp = 1672531200L // 01/01/2023
        val formattedDate = WeatherFormatter.formatDate(timestamp)
        assertEquals("01/01/2023", formattedDate)
    }
}