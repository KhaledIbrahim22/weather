package com.vodafone.weather.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.vodafone.data.local.DB_NAME
import com.vodafone.data.local.WeatherDao
import com.vodafone.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return databaseBuilder(
            context,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

}

