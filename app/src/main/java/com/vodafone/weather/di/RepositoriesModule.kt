package com.vodafone.weather.di

import com.vodafone.core.network.NetworkUtils
import com.vodafone.data.manager.NetworkManger
import com.vodafone.data.repository.CurrentRepositoryImpl
import com.vodafone.data.repository.ForecastRepositoryImpl
import com.vodafone.domain.repository.CurrentWeatherRepository
import com.vodafone.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideCurrentUseCase(networkManger: NetworkManger, networkUtils: NetworkUtils): CurrentWeatherRepository {
        return CurrentRepositoryImpl(networkManger, networkUtils)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(networkManger: NetworkManger, networkUtils: NetworkUtils): ForecastRepository {
        return ForecastRepositoryImpl(networkManger, networkUtils)
    }
}