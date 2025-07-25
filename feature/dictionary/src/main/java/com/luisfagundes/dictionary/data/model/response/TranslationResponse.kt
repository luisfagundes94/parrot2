package com.luisfagundes.dictionary.data.model.response

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("text")
    val text: String,
    @SerializedName("pos")
    val pos: String,
    @SerializedName("examples")
    val examples: List<ExampleResponse>? = null,
    @SerializedName("usage_frequency")
    val usageFrequency: String? = null
)