package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.SentenceTranslation

interface SentenceTranslationRepository {
    fun getById(id: Int): Flow<SentenceTranslation?>
    fun getBySentence(sentenceId: Int, language: String? = null): Flow<List<SentenceTranslation>>
    fun getByLanguage(language: String): Flow<List<SentenceTranslation>>
    fun search(query: String): Flow<List<SentenceTranslation>>
}
