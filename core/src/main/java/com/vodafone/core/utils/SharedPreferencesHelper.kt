package com.vodafone.core.utils

import android.content.Context
import com.vodafone.core.utils.constants.SharedPreferencesKeys.CITY_NAME
import com.vodafone.core.utils.constants.SharedPreferencesKeys.SHARED_PREFERENCE_KEYS
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesHelper @Inject constructor(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_KEYS, Context.MODE_PRIVATE)

    fun saveSearchedCity(city: String) {
        sharedPreferences.edit().putString(CITY_NAME, city).apply()
    }

    fun getSearchedCity(): String? {
        return sharedPreferences.getString(CITY_NAME, null)
    }
}