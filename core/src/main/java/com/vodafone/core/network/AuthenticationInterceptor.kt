package com.vodafone.core.network

import com.vodafone.core.BuildConfig.API_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("key", API_TOKEN).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}