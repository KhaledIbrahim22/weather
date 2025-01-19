package com.vodafone.data.repository

import com.vodafone.core.network.NetworkUtils
import com.vodafone.core.network.State
import com.vodafone.data.mapper.toModel
import com.vodafone.data.manager.NetworkManger
import com.vodafone.domain.models.forecast.ForecastResponseModel
import com.vodafone.domain.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(private val networkManger: NetworkManger, private val networkUtils: NetworkUtils) :
    ForecastRepository {
    override suspend fun getForecast(cityName: String): Flow<State<ForecastResponseModel>> = flow {
        try {
            if (networkUtils.isInternetAvailable()) {
                val response = networkManger.getForecast(cityName = cityName)
                response.let {
                    networkManger.local.deleteLocalForecast()
                    networkManger.local.insertLocalForecast(it)
                    emit(State.Success(it.toModel()))
                }
            } else {
                emit(State.Success(networkManger.local.getLocalForecast()[0].toModel()))
            }
        } catch (e: Exception) {
            emit(State.Error("No forecast data for location found."))
        }
    }.onStart {
        emit(State.Loading)
    }.flowOn(Dispatchers.IO)
}
