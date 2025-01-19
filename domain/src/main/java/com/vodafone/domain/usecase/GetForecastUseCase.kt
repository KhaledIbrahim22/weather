package com.vodafone.domain.usecase

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val repo: ForecastRepository) {
    suspend operator fun invoke(cityName: String) = repo.getForecast(cityName)
}