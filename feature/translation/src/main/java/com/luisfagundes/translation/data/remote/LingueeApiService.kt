package com.luisfagundes.translation.data.remote

import com.luisfagundes.translation.data.model.response.WordResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface LingueeApiService {

    @GET("api/v2/translations")
    suspend fun getTranslations(
        @Query("query") query: String,
        @Query("src") src: String,
        @Query("dst") dst: String,
        @Query("guess_direction") guessDirection: Boolean = false,
        @Query("follow_corrections") followCorrections: String = "always"
    ): List<WordResponse>
}