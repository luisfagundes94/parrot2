package com.luisfagundes.dictionary.data.model.request

data class TranslationRequest(
    val query: String,
    val src: String,
    val dst: String,
    val guessDirection: Boolean = false,
    val followCorrections: FollowCorrections = FollowCorrections.ALWAYS
)

enum class FollowCorrections(val value: String) {
    ALWAYS("always"),
    NEVER("never"),
    ON_EMPTY_TRANSLATIONS("on_empty_translations")
}