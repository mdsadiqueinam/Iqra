package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.DailyProgress

@Dao
interface DailyProgressDao {
    @Upsert
    suspend fun upsert(progress: DailyProgress)

    @Query("SELECT * FROM daily_progress WHERE date = :date")
    fun getByDate(date: String): Flow<DailyProgress?>

    @Query("SELECT * FROM daily_progress WHERE date = :date")
    suspend fun getByDateOneShot(date: String): DailyProgress?

    @Query("SELECT * FROM daily_progress ORDER BY date DESC LIMIT :limit")
    fun getRecent(limit: Int = 30): Flow<List<DailyProgress>>

    @Query("SELECT * FROM daily_progress WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getRange(startDate: String, endDate: String): Flow<List<DailyProgress>>

    @Query("SELECT COUNT(*) FROM daily_progress WHERE (wordsReviewed > 0 OR wordsLearned > 0)")
    fun getTotalActiveDays(): Flow<Int>

    @Query("SELECT date FROM daily_progress WHERE (wordsReviewed > 0 OR wordsLearned > 0) ORDER BY date DESC")
    fun getActiveDates(): Flow<List<String>>

    @Query("SELECT date FROM daily_progress WHERE (wordsReviewed > 0 OR wordsLearned > 0) ORDER BY date DESC")
    suspend fun getActiveDatesOneShot(): List<String>
}
