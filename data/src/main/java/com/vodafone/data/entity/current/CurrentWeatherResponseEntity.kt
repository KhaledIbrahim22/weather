package com.vodafone.data.entity.current

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vodafone.data.local.CurrentWeatherConverters

@Entity
@TypeConverters(CurrentWeatherConverters::class)
data class CurrentWeatherResponseEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int ,

    val location: LocationEntity,
    val current: CurrentDayEntity
)
