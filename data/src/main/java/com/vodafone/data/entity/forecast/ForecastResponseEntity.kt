package com.vodafone.data.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vodafone.data.local.ForecastConverters

@Entity
@TypeConverters(ForecastConverters::class)
data class ForecastResponseEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val forecast: ForecastEntity
)