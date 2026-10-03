package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.ExampleDao
import tech.sadique.iqra.data.local.entity.Example

@Singleton
class ExampleRepositoryImpl @Inject constructor(
    private val exampleDao: ExampleDao
) : ExampleRepository {

    override fun getById(id: Int): Flow<Example?> = exampleDao.getById(id)

    override fun getByWord(word: String): Flow<List<Example>> = exampleDao.getByWord(word)

    override fun search(query: String): Flow<List<Example>> = exampleDao.search(query)
}
