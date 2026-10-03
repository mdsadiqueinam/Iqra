package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.WordProgress

@Dao
interface WordProgressDao {
    @Upsert
    suspend fun upsert(progress: WordProgress)

    @Upsert
    suspend fun upsert(progressList: List<WordProgress>)

    @Query("SELECT * FROM word_progress WHERE wordId = :wordId")
    fun getByWordId(wordId: String): Flow<WordProgress?>

    @Query("SELECT * FROM word_progress WHERE wordId = :wordId")
    suspend fun getByWordIdOneShot(wordId: String): WordProgress?

    @Query(
        """
        SELECT * FROM word_progress 
        WHERE nextReviewAt <= :currentTime 
        ORDER BY nextReviewAt ASC 
        LIMIT :limit
        """
    )
    fun getDueWords(currentTime: Long, limit: Int = 20): Flow<List<WordProgress>>

    @Query(
        """
        SELECT * FROM word_progress 
        WHERE incorrectCount > 0 
        ORDER BY (CAST(incorrectCount AS REAL) / (correctCount + incorrectCount)) DESC 
        LIMIT :limit
        """
    )
    fun getWeakWords(limit: Int = 20): Flow<List<WordProgress>>

    @Query("SELECT * FROM word_progress WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<WordProgress>>

    @Query("SELECT COUNT(*) FROM word_progress WHERE state = 'MASTERED'")
    fun getMasteredCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM word_progress WHERE state != 'NEW'")
    fun getLearningCount(): Flow<Int>
}
