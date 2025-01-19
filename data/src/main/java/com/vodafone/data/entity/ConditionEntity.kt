package com.vodafone.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConditionEntity(
    // Not received from API
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val text: String,
    val icon: String
)