package com.vodafone.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vodafone.data.entity.forecast.ForecastEntity

object ForecastConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromForecastEntity(forecastEntity: ForecastEntity?): String? {
        return forecastEntity?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toForecastEntity(forecastEntityJson: String?): ForecastEntity? {
        return forecastEntityJson?.let {
            val type = object : TypeToken<ForecastEntity>() {}.type
            gson.fromJson(it, type)
        }
    }
}