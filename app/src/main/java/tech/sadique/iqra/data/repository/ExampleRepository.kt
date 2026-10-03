package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Example

interface ExampleRepository {
    fun getById(id: Int): Flow<Example?>
    fun getByWord(word: String): Flow<List<Example>>
    fun search(query: String): Flow<List<Example>>
}
