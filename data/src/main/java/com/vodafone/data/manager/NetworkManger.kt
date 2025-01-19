package com.vodafone.data.manager

import com.vodafone.data.entity.current.CurrentWeatherResponseEntity
import com.vodafone.data.entity.forecast.ForecastResponseEntity
import com.vodafone.data.local.WeatherDao
import com.vodafone.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class NetworkManger @Inject constructor(
    private val weatherService: WeatherRemoteDataSource,
    private val weatherDao: WeatherDao
) {
    private val api get() = weatherService
    val local get() = weatherDao

    suspend fun getCurrentWeather(cityName: String): CurrentWeatherResponseEntity = api.getCurrentWeather(cityName = cityName)

    suspend fun getForecast(cityName: String): ForecastResponseEntity = api.getForecast(cityName = cityName)

}