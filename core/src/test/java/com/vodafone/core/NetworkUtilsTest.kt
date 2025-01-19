package com.vodafone.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.vodafone.core.network.NetworkUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class NetworkUtilsTest {

    @Test
    fun `isInternetAvailable returns true when network is connected`() {
        // Mock Context and ConnectivityManager
        val context = mockk<Context>()
        val connectivityManager = mockk<ConnectivityManager>()
        val networkInfo = mockk<NetworkInfo>()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        every { networkInfo.isConnected } returns true

        val networkUtils = NetworkUtils(context)
        assertTrue(networkUtils.isInternetAvailable())
    }

    @Test
    fun `isInternetAvailable returns false when network is not connected`() {
        val context = mockk<Context>()
        val connectivityManager = mockk<ConnectivityManager>()
        val networkInfo = mockk<NetworkInfo>()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        every { networkInfo.isConnected } returns false

        val networkUtils = NetworkUtils(context)
        assertFalse(networkUtils.isInternetAvailable())
    }
}