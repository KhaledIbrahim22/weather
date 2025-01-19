package com.vodafone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vodafone.data.entity.current.CurrentWeatherResponseEntity
import com.vodafone.data.entity.forecast.ForecastResponseEntity

@Database(
    entities = [CurrentWeatherResponseEntity::class, ForecastResponseEntity::class],
    version = VERSION_NUMBER
)
@TypeConverters(ForecastConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}


const val DB_NAME = "WeatherDatabase.db"
private const val VERSION_NUMBER = 2