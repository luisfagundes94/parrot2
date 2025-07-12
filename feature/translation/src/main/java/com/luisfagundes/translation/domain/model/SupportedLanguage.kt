package com.luisfagundes.translation.domain.model

enum class SupportedLanguage(
    val code: String,
    val displayName: String,
    val supportsFormality: Boolean = false
) {
    ARABIC("AR", "Arabic"),
    BULGARIAN("BG", "Bulgarian"),
    CZECH("CS", "Czech"),
    DANISH("DA", "Danish"),
    GERMAN("DE", "German", true),
    GREEK("EL", "Greek"),
    ENGLISH("EN", "English"),
    SPANISH("ES", "Spanish", true),
    ESTONIAN("ET", "Estonian"),
    FINNISH("FI", "Finnish"),
    FRENCH("FR", "French", true),
    HUNGARIAN("HU", "Hungarian"),
    INDONESIAN("ID", "Indonesian"),
    ITALIAN("IT", "Italian", true),
    JAPANESE("JA", "Japanese", true),
    KOREAN("KO", "Korean"),
    LITHUANIAN("LT", "Lithuanian"),
    LATVIAN("LV", "Latvian"),
    NORWEGIAN("NB", "Norwegian"),
    DUTCH("NL", "Dutch", true),
    POLISH("PL", "Polish", true),
    PORTUGUESE("PT", "Portuguese", true),
    ROMANIAN("RO", "Romanian"),
    RUSSIAN("RU", "Russian", true),
    SLOVAK("SK", "Slovak"),
    SLOVENIAN("SL", "Slovenian"),
    SWEDISH("SV", "Swedish"),
    TURKISH("TR", "Turkish"),
    UKRAINIAN("UK", "Ukrainian"),
    CHINESE("ZH", "Chinese");

    val language: Language
        get() = Language(code, displayName, supportsFormality)

    companion object {
        fun findByName(name: String): SupportedLanguage? =
            entries.find { it.displayName.equals(name, ignoreCase = true) }

        fun getAllLanguages(): List<Language> = entries.map { it.language }
    }
}