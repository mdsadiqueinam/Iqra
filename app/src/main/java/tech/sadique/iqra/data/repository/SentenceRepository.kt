package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Sentence

interface SentenceRepository {
    fun getById(id: Int): Flow<Sentence?>
    fun getByWord(word: String): Flow<List<Sentence>>
    fun search(query: String): Flow<List<Sentence>>
}
