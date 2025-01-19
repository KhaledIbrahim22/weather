package com.vodafone.weather.di

import com.vodafone.domain.repository.CurrentWeatherRepository
import com.vodafone.domain.repository.ForecastRepository
import com.vodafone.domain.usecase.GetCurrentWeatherUseCase
import com.vodafone.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideCurrentUseCase(repo: CurrentWeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideForecastUseCase(repo: ForecastRepository): GetForecastUseCase {
        return GetForecastUseCase(repo)
    }
}