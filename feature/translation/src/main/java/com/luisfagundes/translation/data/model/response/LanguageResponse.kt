package com.luisfagundes.translation.data.model.response

import com.google.gson.annotations.SerializedName

data class LanguagesResponse(
    @SerializedName("languages")
    val languages: List<LanguageResponse>
)

data class LanguageResponse(
    @SerializedName("language")
    val language: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("supports_formality")
    val supportsFormality: Boolean? = null
)