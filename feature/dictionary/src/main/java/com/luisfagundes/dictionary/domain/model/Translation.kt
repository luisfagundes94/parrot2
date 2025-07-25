package com.luisfagundes.dictionary.domain.model

internal data class Translation(
    val featured: Boolean,
    val text: String,
    val partOfSpeech: String,
    val examples: List<Example>,
    val usageFrequency: String
)
