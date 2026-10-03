package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Word

interface WordRepository {
    suspend fun upsert(word: Word)
    suspend fun upsert(words: List<Word>)
    fun getById(id: String): Flow<Word?>
    fun search(query: String, quranic: Boolean? = null): Flow<List<Word>>
    fun getByType(type: String, quranic: Boolean? = null): Flow<List<Word>>
    fun getByCategory(category: String, quranic: Boolean? = null): Flow<List<Word>>
}
