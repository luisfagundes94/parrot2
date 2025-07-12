package com.luisfagundes.translation.data.network

import com.luisfagundes.translation.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DeepLAuthInterceptor @Inject constructor() : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "DeepL-Auth-Key ${BuildConfig.DEEPL_API_KEY}")
            .header("Content-Type", "application/json")
            .build()
        
        return chain.proceed(authenticatedRequest)
    }
}