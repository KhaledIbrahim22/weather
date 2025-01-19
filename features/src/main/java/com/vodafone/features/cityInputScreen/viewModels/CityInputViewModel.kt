package com.vodafone.features.cityInputScreen.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vodafone.core.network.State
import com.vodafone.core.utils.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val preferences: SharedPreferencesHelper
) : ViewModel() {
    private val _getState = MutableLiveData<State<String?>>()
    val getState: LiveData<State<String?>> = _getState

    init {
        checkLastSearchedCity()
    }

    private fun checkLastSearchedCity() {
        runBlocking {
            _getState.postValue(State.Loading)
            preferences.getSearchedCity().let {
                _getState.postValue(State.Success(it))
            }
        }
    }

    private val _setState = MutableLiveData<State<Unit>>()
    val setState: LiveData<State<Unit>> = _setState
    fun setCityName(city: String) {
        runBlocking {
            _setState.postValue(State.Loading)
            preferences.saveSearchedCity(city = city).let {
                _setState.postValue(State.Success(it))
            }
        }
    }
}
