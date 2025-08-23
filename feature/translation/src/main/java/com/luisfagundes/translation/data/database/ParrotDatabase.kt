package com.luisfagundes.translation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luisfagundes.translation.data.database.dao.TranslationHistoryDao
import com.luisfagundes.translation.data.database.entities.SavedTranslationEntity

@Database(
    entities = [SavedTranslationEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class ParrotDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao
    
    companion object {
        const val DATABASE_NAME = "parrot_database"
    }
}