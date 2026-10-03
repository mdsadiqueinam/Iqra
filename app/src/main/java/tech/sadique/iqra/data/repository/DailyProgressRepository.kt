package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.DailyProgress

interface DailyProgressRepository {
    suspend fun upsert(progress: DailyProgress)
    fun getByDate(date: String): Flow<DailyProgress?>
    fun getRecent(limit: Int = 30): Flow<List<DailyProgress>>
    fun getRange(startDate: String, endDate: String): Flow<List<DailyProgress>>
    fun getTotalActiveDays(): Flow<Int>
    suspend fun recordReview(
        isCorrect: Boolean,
        isNewWord: Boolean,
        date: String
    )
    suspend fun addStudyDuration(
        durationSeconds: Long,
        date: String
    )
    suspend fun calculateCurrentStreak(todayDate: String): Int
}
