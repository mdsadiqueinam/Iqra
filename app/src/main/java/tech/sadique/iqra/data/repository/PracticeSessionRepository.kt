package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.PracticeSession

interface PracticeSessionRepository {
    suspend fun recordSession(session: PracticeSession): Long
    fun getAll(): Flow<List<PracticeSession>>
    fun getRecent(limit: Int = 10): Flow<List<PracticeSession>>
}
