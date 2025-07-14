package com.luisfagundes.dictionary.di

import com.luisfagundes.dictionary.BuildConfig
import com.luisfagundes.dictionary.data.remote.LingueeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val BASE_URL = if (BuildConfig.DEBUG) {
    "http://10.0.2.2:3000/"
} else {
    "https://linguee-api.fly.dev/"
}

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLingueeApiService(retrofit: Retrofit): LingueeApiService {
        return retrofit.create(LingueeApiService::class.java)
    }
}