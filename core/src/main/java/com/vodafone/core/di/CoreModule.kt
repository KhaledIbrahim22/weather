package com.vodafone.core.di

import android.content.Context
import com.vodafone.core.network.NetworkUtils
import com.vodafone.core.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}