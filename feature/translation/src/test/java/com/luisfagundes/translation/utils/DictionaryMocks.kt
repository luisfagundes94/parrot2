package com.luisfagundes.translation.utils

import com.luisfagundes.translation.domain.model.AudioLink
import com.luisfagundes.translation.domain.model.Example
import com.luisfagundes.translation.domain.model.Translation
import com.luisfagundes.translation.domain.model.Word

internal val mockWords = listOf(
    Word(
        featured = true,
        translatedText = "hello",
        partOfSpeech = "noun",
        forms = listOf("hello", "hellos"),
        grammarInfo = "countable",
        audioLinks = listOf(AudioLink("audio_url", "mp3")),
        translations = listOf(
            Translation(
                featured = true,
                text = "olá",
                partOfSpeech = "interjeição",
                examples = emptyList(),
                usageFrequency = "common"
            )
        )
    )
)

internal val mockWordsWithExamples = listOf(
    Word(
        featured = true,
        translatedText = "hello",
        partOfSpeech = "noun",
        forms = listOf("hello"),
        grammarInfo = "",
        audioLinks = emptyList(),
        translations = listOf(
            Translation(
                featured = true,
                text = "olá",
                partOfSpeech = "interjeição",
                examples = listOf(
                    Example(
                        sourceLanguage = "Hello, world!",
                        targetLanguage = "Olá, mundo!"
                    )
                ),
                usageFrequency = "common"
            )
        )
    )
)