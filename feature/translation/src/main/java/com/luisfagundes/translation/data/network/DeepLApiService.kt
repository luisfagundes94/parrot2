package com.luisfagundes.translation.data.network

import com.luisfagundes.translation.data.model.response.LanguageListResponse
import com.luisfagundes.translation.data.model.request.TranslationRequest
import com.luisfagundes.translation.data.model.response.TranslationListResponse
import com.luisfagundes.translation.data.model.response.ApiUsageResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface DeepLApiService {
    
    @POST("translate")
    suspend fun translate(
        @Body request: TranslationRequest
    ): TranslationListResponse
    
    @GET("languages")
    suspend fun getSourceLanguages(
        @Query("type") type: String = "source"
    ): LanguageListResponse
    
    @GET("languages")
    suspend fun getTargetLanguages(
        @Query("type") type: String = "target"
    ): LanguageListResponse
    
    @GET("usage")
    suspend fun getUsage(): ApiUsageResponse
}