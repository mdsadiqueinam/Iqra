package tech.sadique.iqra.data.repository

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.WordProgressDao
import tech.sadique.iqra.data.local.entity.WordProgress
import tech.sadique.iqra.domain.srs.SpacedRepetitionEngine

@Singleton
class WordProgressRepositoryImpl @Inject constructor(
    private val wordProgressDao: WordProgressDao,
    private val dailyProgressRepository: DailyProgressRepository
) : WordProgressRepository {

    override suspend fun upsert(progress: WordProgress) =
        wordProgressDao.upsert(progress)

    override suspend fun upsert(progressList: List<WordProgress>) =
        wordProgressDao.upsert(progressList)

    override fun getByWordId(wordId: String): Flow<WordProgress?> =
        wordProgressDao.getByWordId(wordId)

    override fun getDueWords(currentTime: Long, limit: Int): Flow<List<WordProgress>> =
        wordProgressDao.getDueWords(currentTime, limit)

    override fun getWeakWords(limit: Int): Flow<List<WordProgress>> =
        wordProgressDao.getWeakWords(limit)

    override fun getFavorites(): Flow<List<WordProgress>> =
        wordProgressDao.getFavorites()

    override fun getMasteredCount(): Flow<Int> =
        wordProgressDao.getMasteredCount()

    override fun getLearningCount(): Flow<Int> =
        wordProgressDao.getLearningCount()

    override suspend fun recordReview(
        wordId: String,
        isCorrect: Boolean,
        currentTime: Long
    ): WordProgress {
        val current = wordProgressDao.getByWordIdOneShot(wordId) ?: WordProgress(wordId = wordId)
        val isNewWord = current.state == WordProgress.State.NEW
        val updated = SpacedRepetitionEngine.calculateNextReview(current, isCorrect, currentTime)
        wordProgressDao.upsert(updated)

        val todayDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        dailyProgressRepository.recordReview(
            isCorrect = isCorrect,
            isNewWord = isNewWord,
            date = todayDate
        )

        return updated
    }

    override suspend fun toggleFavorite(wordId: String) {
        val current = wordProgressDao.getByWordIdOneShot(wordId) ?: WordProgress(wordId = wordId)
        wordProgressDao.upsert(current.copy(isFavorite = !current.isFavorite))
    }
}
