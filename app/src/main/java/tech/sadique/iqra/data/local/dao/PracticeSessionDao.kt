package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.PracticeSession

@Dao
interface PracticeSessionDao {
    @Upsert
    suspend fun upsert(session: PracticeSession): Long

    @Query("SELECT * FROM practice_sessions ORDER BY completedAt DESC")
    fun getAll(): Flow<List<PracticeSession>>

    @Query("SELECT * FROM practice_sessions ORDER BY completedAt DESC LIMIT :limit")
    fun getRecent(limit: Int = 10): Flow<List<PracticeSession>>
}
