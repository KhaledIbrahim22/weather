package com.vodafone.data.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ForecastEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("forecastday")
    val forecastList: List<ForecastDayEntity>
)