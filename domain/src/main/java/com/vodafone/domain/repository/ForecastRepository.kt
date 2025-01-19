package com.vodafone.domain.repository

import com.vodafone.core.network.State
import com.vodafone.domain.models.forecast.ForecastResponseModel
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecast(cityName: String): Flow<State<ForecastResponseModel>>
}
