package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.WordMeaningDao
import tech.sadique.iqra.data.local.entity.WordMeaning

@Singleton
class WordMeaningRepositoryImpl @Inject constructor(
    private val wordMeaningDao: WordMeaningDao
) : WordMeaningRepository {

    override suspend fun upsert(meaning: WordMeaning): Long =
        wordMeaningDao.upsert(meaning)

    override suspend fun upsert(meanings: List<WordMeaning>): List<Long> =
        wordMeaningDao.upsert(meanings)

    override fun getById(id: Int): Flow<WordMeaning?> =
        wordMeaningDao.getById(id)

    override fun getByWord(wordId: String, language: String?): Flow<List<WordMeaning>> =
        wordMeaningDao.getByWord(wordId, language)

    override fun getByLanguage(language: String): Flow<List<WordMeaning>> =
        wordMeaningDao.getByLanguage(language)

    override fun search(query: String): Flow<List<WordMeaning>> =
        wordMeaningDao.search(query)
}
