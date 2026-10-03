package tech.sadique.iqra.data.repository

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.PracticeSessionDao
import tech.sadique.iqra.data.local.entity.PracticeSession

@Singleton
class PracticeSessionRepositoryImpl @Inject constructor(
    private val practiceSessionDao: PracticeSessionDao,
    private val dailyProgressRepository: DailyProgressRepository
) : PracticeSessionRepository {

    override suspend fun recordSession(session: PracticeSession): Long {
        val id = practiceSessionDao.upsert(session)
        val durationSeconds = ((session.completedAt - session.startedAt) / 1000L).coerceAtLeast(0L)
        val todayDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        dailyProgressRepository.addStudyDuration(durationSeconds, todayDate)
        return id
    }

    override fun getAll(): Flow<List<PracticeSession>> =
        practiceSessionDao.getAll()

    override fun getRecent(limit: Int): Flow<List<PracticeSession>> =
        practiceSessionDao.getRecent(limit)
}
