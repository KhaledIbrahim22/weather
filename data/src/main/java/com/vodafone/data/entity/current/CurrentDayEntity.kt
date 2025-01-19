package com.vodafone.data.entity.current

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vodafone.data.entity.ConditionEntity

@Entity
data class CurrentDayEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("temp_c")
    val temp: Double,
    val condition: ConditionEntity
)