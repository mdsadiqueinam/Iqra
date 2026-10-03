package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Meaning

interface MeaningRepository {
    fun getById(id: Int): Flow<Meaning?>
    fun getByExample(exampleId: Int, language: String? = null): Flow<List<Meaning>>
    fun getByLanguage(language: String): Flow<List<Meaning>>
    fun search(query: String): Flow<List<Meaning>>
}
