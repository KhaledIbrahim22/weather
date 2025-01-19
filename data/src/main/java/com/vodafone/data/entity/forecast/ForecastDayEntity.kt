package com.vodafone.data.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForecastDayEntity(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val day: DayEntity
)