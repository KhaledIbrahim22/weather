package com.vodafone.weather.di

import com.vodafone.core.network.AuthenticationInterceptor
import com.vodafone.core.BuildConfig.BASE_URL
import com.vodafone.data.remote.WeatherRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteApiModule {

    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(15L, TimeUnit.SECONDS)
        httpClient.readTimeout(15L, TimeUnit.SECONDS)
        httpClient.writeTimeout(15L, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)
            .addInterceptor(AuthenticationInterceptor())
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): WeatherRemoteDataSource {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherRemoteDataSource::class.java)
    }

}

