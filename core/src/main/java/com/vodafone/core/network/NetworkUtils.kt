package com.vodafone.core.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor(val context: Context) {

    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }
}