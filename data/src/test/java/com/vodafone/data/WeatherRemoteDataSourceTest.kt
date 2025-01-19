package com.vodafone.data

import com.vodafone.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: WeatherRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherRemoteDataSource::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetchWeather returns success on valid response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody("""{"temperature": 300.0, "city": "Egypt"}""")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
    }
}