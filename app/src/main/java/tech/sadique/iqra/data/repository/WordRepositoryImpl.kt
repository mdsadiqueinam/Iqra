package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.WordDao
import tech.sadique.iqra.data.local.entity.Word

@Singleton
class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : WordRepository {

    override fun getById(id: String): Flow<Word?> = wordDao.getById(id)

    override fun search(query: String, quranic: Boolean?): Flow<List<Word>> =
        wordDao.search(query, quranic)

    override fun getByType(type: String, quranic: Boolean?): Flow<List<Word>> =
        wordDao.getByType(type, quranic)

    override fun getByCategory(category: String, quranic: Boolean?): Flow<List<Word>> =
        wordDao.getByCategory(category, quranic)
}
