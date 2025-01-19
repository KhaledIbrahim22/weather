package com.vodafone.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vodafone.data.entity.current.CurrentDayEntity
import com.vodafone.data.entity.current.LocationEntity
import java.lang.reflect.Type

object CurrentWeatherConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromLocationEntity(locationEntity: LocationEntity?): String? {
        return if (locationEntity == null) {
            null
        } else gson.toJson(locationEntity)
    }

    @TypeConverter
    fun toLocationEntity(locationJson: String?): LocationEntity? {
        if (locationJson == null) {
            return null
        }
        val type: Type = object : TypeToken<LocationEntity?>() {}.type
        return gson.fromJson(locationJson, type)
    }

    @TypeConverter
    fun fromCurrentDayEntity(currentEntity: CurrentDayEntity?): String? {
        return currentEntity?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCurrentDayEntity(currentEntityJson: String?): CurrentDayEntity? {
        return currentEntityJson?.let {
            val type = object : TypeToken<CurrentDayEntity>() {}.type
            gson.fromJson(it, type)
        }
    }
}