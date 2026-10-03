package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.SentenceDao
import tech.sadique.iqra.data.local.entity.Sentence

@Singleton
class SentenceRepositoryImpl @Inject constructor(
    private val sentenceDao: SentenceDao
) : SentenceRepository {

    override fun getById(id: Int): Flow<Sentence?> = sentenceDao.getById(id)

    override fun getByWord(word: String): Flow<List<Sentence>> = sentenceDao.getByWord(word)

    override fun search(query: String): Flow<List<Sentence>> = sentenceDao.search(query)
}
