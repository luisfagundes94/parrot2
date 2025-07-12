package com.luisfagundes.translation.domain.model

data class Translation(
    val translatedText: String,
    val detectedSourceLanguage: String?
)