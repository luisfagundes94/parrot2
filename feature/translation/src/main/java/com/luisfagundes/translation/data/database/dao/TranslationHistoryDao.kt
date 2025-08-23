package com.luisfagundes.translation.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luisfagundes.translation.data.database.entities.SavedTranslationEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TranslationHistoryDao {
    
    @Query("SELECT * FROM translation_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<SavedTranslationEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(translation: SavedTranslationEntity)
    
    @Delete
    suspend fun deleteTranslation(translation: SavedTranslationEntity)
    
    @Query("DELETE FROM translation_history WHERE id = :id")
    suspend fun deleteTranslationById(id: Long)
    
    @Query("DELETE FROM translation_history")
    suspend fun deleteAllHistory()
    
    @Query("SELECT COUNT(*) FROM translation_history")
    suspend fun getHistoryCount(): Int
}