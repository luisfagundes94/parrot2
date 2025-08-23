package com.luisfagundes.translation.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luisfagundes.translation.data.database.entities.TranslationHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TranslationHistoryDao {
    
    @Query("SELECT * FROM translation_history ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<TranslationHistoryEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(translation: TranslationHistoryEntity)
    
    @Delete
    suspend fun deleteTranslation(translation: TranslationHistoryEntity)
    
    @Query("DELETE FROM translation_history WHERE id = :id")
    suspend fun deleteTranslationById(id: Long)
    
    @Query("DELETE FROM translation_history")
    suspend fun deleteAllHistory()
    
    @Query("SELECT COUNT(*) FROM translation_history")
    suspend fun getHistoryCount(): Int
}