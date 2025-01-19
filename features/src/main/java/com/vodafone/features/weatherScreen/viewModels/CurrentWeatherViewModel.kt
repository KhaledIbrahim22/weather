package com.vodafone.features.weatherScreen.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.core.network.State
import com.vodafone.core.utils.SharedPreferencesHelper
import com.vodafone.domain.models.current.CurrentWeatherResponseModel
import com.vodafone.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val useCase: GetCurrentWeatherUseCase,
    val preferences: SharedPreferencesHelper
) : ViewModel() {
    private val _state = MutableLiveData<State<CurrentWeatherResponseModel>>()
    val state: LiveData<State<CurrentWeatherResponseModel>> get() = _state

    fun getCurrentWeather(cityName: String) {
        viewModelScope.launch {
            useCase(cityName).catch {
                _state.postValue(State.Error(message = it.message))
            }.collect { _state.postValue(it) }
        }
    }
}