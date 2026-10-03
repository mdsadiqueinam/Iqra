package tech.sadique.iqra.data.repository

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.DailyProgressDao
import tech.sadique.iqra.data.local.entity.DailyProgress

@Singleton
class DailyProgressRepositoryImpl @Inject constructor(
    private val dailyProgressDao: DailyProgressDao
) : DailyProgressRepository {

    override suspend fun upsert(progress: DailyProgress) =
        dailyProgressDao.upsert(progress)

    override fun getByDate(date: String): Flow<DailyProgress?> =
        dailyProgressDao.getByDate(date)

    override fun getRecent(limit: Int): Flow<List<DailyProgress>> =
        dailyProgressDao.getRecent(limit)

    override fun getRange(startDate: String, endDate: String): Flow<List<DailyProgress>> =
        dailyProgressDao.getRange(startDate, endDate)

    override fun getTotalActiveDays(): Flow<Int> =
        dailyProgressDao.getTotalActiveDays()

    override suspend fun recordReview(isCorrect: Boolean, isNewWord: Boolean, date: String) {
        val existing = dailyProgressDao.getByDateOneShot(date) ?: DailyProgress(date = date)
        val updated = existing.copy(
            wordsLearned = if (isNewWord) existing.wordsLearned + 1 else existing.wordsLearned,
            wordsReviewed = existing.wordsReviewed + 1,
            correctCount = if (isCorrect) existing.correctCount + 1 else existing.correctCount,
            incorrectCount = if (!isCorrect) existing.incorrectCount + 1 else existing.incorrectCount
        )
        dailyProgressDao.upsert(updated)
    }

    override suspend fun addStudyDuration(durationSeconds: Long, date: String) {
        val existing = dailyProgressDao.getByDateOneShot(date) ?: DailyProgress(date = date)
        val updated = existing.copy(
            timeSpentSeconds = existing.timeSpentSeconds + durationSeconds
        )
        dailyProgressDao.upsert(updated)
    }

    override suspend fun calculateCurrentStreak(todayDate: String): Int {
        val activeDates = dailyProgressDao.getActiveDatesOneShot().toSet()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        var currentDate = LocalDate.parse(todayDate, formatter)

        if (!activeDates.contains(todayDate)) {
            currentDate = currentDate.minusDays(1)
        }

        var streak = 0
        while (activeDates.contains(currentDate.format(formatter))) {
            streak++
            currentDate = currentDate.minusDays(1)
        }
        return streak
    }
}
