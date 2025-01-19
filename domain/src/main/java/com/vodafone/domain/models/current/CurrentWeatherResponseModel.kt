package com.vodafone.domain.models.current

import com.vodafone.domain.models.DayModel

data class CurrentWeatherResponseModel(
    val location: LocationModel,
    val current: DayModel
)