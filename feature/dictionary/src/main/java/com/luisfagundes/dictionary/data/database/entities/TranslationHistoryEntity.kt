package com.luisfagundes.dictionary.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translation_history")
internal data class TranslationHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "query")
    val query: String,
    
    @ColumnInfo(name = "source_language")
    val sourceLanguage: String,
    
    @ColumnInfo(name = "target_language")
    val targetLanguage: String,
    
    @ColumnInfo(name = "translated_text")
    val translatedText: String,
    
    @ColumnInfo(name = "part_of_speech")
    val partOfSpeech: String,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)