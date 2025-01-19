package com.vodafone.data.entity.current

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,
    val region: String,
    val country: String
)