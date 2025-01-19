package com.vodafone.features.weatherScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.core.network.State
import com.vodafone.domain.models.forecast.ForecastResponseModel
import com.vodafone.domain.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val useCase: GetForecastUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State<ForecastResponseModel>>(State.Loading)
    val state: StateFlow<State<ForecastResponseModel>> get() = _state

    fun processIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.GetForecastIntent -> getForecast(intent.cityName)
        }
    }

    private fun getForecast(cityName: String) {
        viewModelScope.launch {
            useCase(cityName).catch {
                _state.value = State.Error(message = it.message)
            }.collect { _state.value = it }
        }
    }
}

sealed class ForecastIntent {
    data class GetForecastIntent(val cityName: String) : ForecastIntent()
}