package com.luisfagundes.translation.data.model.response

import com.google.gson.annotations.SerializedName

data class WordResponse(
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("text")
    val text: String,
    @SerializedName("pos")
    val pos: String,
    @SerializedName("forms")
    val forms: List<String>,
    @SerializedName("grammar_info")
    val grammarInfo: String? = null,
    @SerializedName("audio_links")
    val audioLinks: List<AudioLinkResponse>,
    @SerializedName("translations")
    val translations: List<TranslationResponse>
)