package com.vodafone.domain.repository

import com.vodafone.core.network.State
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(cityName: String): Flow<State<CurrentWeatherResponseModel>>
}
