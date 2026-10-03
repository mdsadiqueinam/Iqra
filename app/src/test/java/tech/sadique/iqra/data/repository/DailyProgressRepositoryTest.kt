package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import tech.sadique.iqra.data.local.dao.DailyProgressDao
import tech.sadique.iqra.data.local.entity.DailyProgress

class DailyProgressRepositoryTest {

    private class FakeDailyProgressDao : DailyProgressDao {
        val storage = mutableMapOf<String, DailyProgress>()
        var activeDates: List<String> = emptyList()

        override suspend fun upsert(progress: DailyProgress) {
            storage[progress.date] = progress
        }

        override fun getByDate(date: String): Flow<DailyProgress?> = emptyFlow()

        override suspend fun getByDateOneShot(date: String): DailyProgress? = storage[date]

        override fun getRecent(limit: Int): Flow<List<DailyProgress>> = emptyFlow()

        override fun getRange(startDate: String, endDate: String): Flow<List<DailyProgress>> = emptyFlow()

        override fun getTotalActiveDays(): Flow<Int> = emptyFlow()

        override fun getActiveDates(): Flow<List<String>> = emptyFlow()

        override suspend fun getActiveDatesOneShot(): List<String> = activeDates
    }

    @Test
    fun `calculateCurrentStreak returns correct streak when active today`() = runBlocking {
        val fakeDao = FakeDailyProgressDao().apply {
            activeDates = listOf("2026-10-03", "2026-10-02", "2026-10-01", "2026-09-30")
        }
        val repository = DailyProgressRepositoryImpl(fakeDao)
        val streak = repository.calculateCurrentStreak("2026-10-03")

        assertEquals(4, streak)
    }

    @Test
    fun `calculateCurrentStreak returns streak from yesterday if not studied yet today`() = runBlocking {
        val fakeDao = FakeDailyProgressDao().apply {
            activeDates = listOf("2026-10-02", "2026-10-01", "2026-09-30")
        }
        val repository = DailyProgressRepositoryImpl(fakeDao)
        val streak = repository.calculateCurrentStreak("2026-10-03")

        assertEquals(3, streak)
    }

    @Test
    fun `calculateCurrentStreak returns 0 if missed yesterday and today`() = runBlocking {
        val fakeDao = FakeDailyProgressDao().apply {
            activeDates = listOf("2026-10-01", "2026-09-30")
        }
        val repository = DailyProgressRepositoryImpl(fakeDao)
        val streak = repository.calculateCurrentStreak("2026-10-03")

        assertEquals(0, streak)
    }

    @Test
    fun `calculateCurrentStreak returns 0 when no active dates`() = runBlocking {
        val fakeDao = FakeDailyProgressDao()
        val repository = DailyProgressRepositoryImpl(fakeDao)
        val streak = repository.calculateCurrentStreak("2026-10-03")

        assertEquals(0, streak)
    }

    @Test
    fun `recordReview increments counts properly`() = runBlocking {
        val fakeDao = FakeDailyProgressDao()
        val repository = DailyProgressRepositoryImpl(fakeDao)

        repository.recordReview(isCorrect = true, isNewWord = true, date = "2026-10-03")
        var recorded = fakeDao.storage["2026-10-03"]
        assertEquals(1, recorded?.wordsLearned)
        assertEquals(1, recorded?.wordsReviewed)
        assertEquals(1, recorded?.correctCount)
        assertEquals(0, recorded?.incorrectCount)

        repository.recordReview(isCorrect = false, isNewWord = false, date = "2026-10-03")
        recorded = fakeDao.storage["2026-10-03"]
        assertEquals(1, recorded?.wordsLearned)
        assertEquals(2, recorded?.wordsReviewed)
        assertEquals(1, recorded?.correctCount)
        assertEquals(1, recorded?.incorrectCount)
    }
}
