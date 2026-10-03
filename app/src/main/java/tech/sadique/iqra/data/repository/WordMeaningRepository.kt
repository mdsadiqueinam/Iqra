package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.WordMeaning

interface WordMeaningRepository {
    suspend fun upsert(meaning: WordMeaning): Long
    suspend fun upsert(meanings: List<WordMeaning>): List<Long>
    fun getById(id: Int): Flow<WordMeaning?>
    fun getByWord(wordId: String, language: String? = null): Flow<List<WordMeaning>>
    fun getByLanguage(language: String): Flow<List<WordMeaning>>
    fun search(query: String): Flow<List<WordMeaning>>
}
