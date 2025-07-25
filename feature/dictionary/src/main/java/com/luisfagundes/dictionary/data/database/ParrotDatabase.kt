package com.luisfagundes.dictionary.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.luisfagundes.dictionary.data.database.dao.TranslationHistoryDao
import com.luisfagundes.dictionary.data.database.entities.TranslationHistoryEntity

@Database(
    entities = [TranslationHistoryEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class ParrotDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao
    
    companion object {
        const val DATABASE_NAME = "parrot_database"
    }
}