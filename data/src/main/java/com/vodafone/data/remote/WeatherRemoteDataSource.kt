package com.vodafone.data.remote

import com.vodafone.data.entity.current.CurrentWeatherResponseEntity
import com.vodafone.data.entity.forecast.ForecastResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDataSource {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String
    ): CurrentWeatherResponseEntity

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("days") days: Int = 7,
        @Query("hour") hour: Int = 24,
    ): ForecastResponseEntity
}
