package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.SentenceTranslation

interface SentenceTranslationRepository {
    suspend fun upsert(translation: SentenceTranslation): Long
    suspend fun upsert(translations: List<SentenceTranslation>): List<Long>
    fun getById(id: Int): Flow<SentenceTranslation?>
    fun getBySentence(sentenceId: Int, language: String? = null): Flow<List<SentenceTranslation>>
    fun getByLanguage(language: String): Flow<List<SentenceTranslation>>
    fun search(query: String): Flow<List<SentenceTranslation>>
}
