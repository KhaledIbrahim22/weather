package com.vodafone.data.entity.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vodafone.data.entity.ConditionEntity

@Entity
data class DayEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("avgtemp_c")
    val temp: Double,
    val condition: ConditionEntity
)