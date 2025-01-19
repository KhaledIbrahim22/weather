package com.vodafone.data.repository

import com.vodafone.core.network.NetworkUtils
import com.vodafone.core.network.State
import com.vodafone.data.manager.NetworkManger
import com.vodafone.data.mapper.toModel
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import com.vodafone.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CurrentRepositoryImpl @Inject constructor(private val networkManger: NetworkManger, private val networkUtils: NetworkUtils) :
    CurrentWeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): Flow<State<CurrentWeatherResponseModel>> = flow {
        try {
            if (networkUtils.isInternetAvailable()) {
                val response = networkManger.getCurrentWeather(cityName = cityName)
                response.let {
                    networkManger.local.deleteLocalCurrent()
                    networkManger.local.insertLocalCurrent(it)
                    emit(State.Success(it.toModel()))
                }
            } else {
                emit(State.Success(networkManger.local.getLocalCurrent()[0].toModel()))
            }
        } catch (e: Exception) {
            emit(State.Error("No current weather data for location found."))
        }
    }.onStart {
        emit(State.Loading)
    }.flowOn(Dispatchers.IO)
}
