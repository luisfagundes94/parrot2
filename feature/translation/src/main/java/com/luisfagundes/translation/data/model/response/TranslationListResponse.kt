package com.luisfagundes.translation.data.model.response

import com.google.gson.annotations.SerializedName

internal data class TranslationListResponse(
    @SerializedName("translations")
    val translations: List<TranslationResponse>
)

internal data class TranslationResponse(
    @SerializedName("detected_source_language")
    val detectedSourceLanguage: String?,
    @SerializedName("text")
    val text: String
)