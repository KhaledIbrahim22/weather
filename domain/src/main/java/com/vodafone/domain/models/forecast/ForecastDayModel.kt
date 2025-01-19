package com.vodafone.domain.models.forecast

import com.vodafone.domain.models.DayModel

data class ForecastDayModel(
    val date: String,
    val day: DayModel
)