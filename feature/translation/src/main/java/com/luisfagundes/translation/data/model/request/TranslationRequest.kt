package com.luisfagundes.translation.data.model.request

import com.google.gson.annotations.SerializedName

data class TranslationRequest(
    @SerializedName("text")
    val text: List<String>,
    @SerializedName("source_lang")
    val sourceLang: String? = null,
    @SerializedName("target_lang")
    val targetLang: String,
    @SerializedName("formality")
    val formality: String? = null,
    @SerializedName("preserve_formatting")
    val preserveFormatting: Boolean? = null
)