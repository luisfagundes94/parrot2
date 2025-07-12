package com.luisfagundes.translation.domain.model

data class Language(
    val code: String,
    val name: String,
    val supportsFormality: Boolean = false
)