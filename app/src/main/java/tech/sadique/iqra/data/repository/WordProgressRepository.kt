package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.WordProgress

interface WordProgressRepository {
    suspend fun upsert(progress: WordProgress)
    suspend fun upsert(progressList: List<WordProgress>)
    fun getByWordId(wordId: String): Flow<WordProgress?>
    fun getDueWords(currentTime: Long, limit: Int = 20): Flow<List<WordProgress>>
    fun getWeakWords(limit: Int = 20): Flow<List<WordProgress>>
    fun getFavorites(): Flow<List<WordProgress>>
    fun getMasteredCount(): Flow<Int>
    fun getLearningCount(): Flow<Int>
    suspend fun recordReview(
        wordId: String,
        isCorrect: Boolean,
        currentTime: Long = System.currentTimeMillis()
    ): WordProgress
    suspend fun toggleFavorite(wordId: String)
}
