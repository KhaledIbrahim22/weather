package com.vodafone.domain.usecase

import com.vodafone.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val repository: CurrentWeatherRepository) {
    suspend operator fun invoke(cityName: String) = repository.getCurrentWeather(cityName = cityName)

}