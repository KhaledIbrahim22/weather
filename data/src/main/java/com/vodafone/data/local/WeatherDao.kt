package com.vodafone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vodafone.data.entity.current.CurrentWeatherResponseEntity
import com.vodafone.data.entity.forecast.ForecastResponseEntity

@Dao
interface WeatherDao {
    @Insert
    fun insertLocalForecast(forecast: ForecastResponseEntity)

    @Query("SELECT * FROM ForecastResponseEntity")
    fun getLocalForecast(): List<ForecastResponseEntity>

    @Query("DELETE FROM ForecastResponseEntity")
    fun deleteLocalForecast()


    @Insert
    fun insertLocalCurrent(current: CurrentWeatherResponseEntity)

    @Query("SELECT * FROM CurrentWeatherResponseEntity")
    fun getLocalCurrent(): List<CurrentWeatherResponseEntity>

    @Query("DELETE FROM CurrentWeatherResponseEntity")
    fun deleteLocalCurrent()
}
