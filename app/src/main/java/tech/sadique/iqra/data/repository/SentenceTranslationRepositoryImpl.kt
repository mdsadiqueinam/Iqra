package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.SentenceTranslationDao
import tech.sadique.iqra.data.local.entity.SentenceTranslation

@Singleton
class SentenceTranslationRepositoryImpl @Inject constructor(
    private val sentenceTranslationDao: SentenceTranslationDao
) : SentenceTranslationRepository {

    override suspend fun upsert(translation: SentenceTranslation): Long =
        sentenceTranslationDao.upsert(translation)

    override suspend fun upsert(translations: List<SentenceTranslation>): List<Long> =
        sentenceTranslationDao.upsert(translations)

    override fun getById(id: Int): Flow<SentenceTranslation?> =
        sentenceTranslationDao.getById(id)

    override fun getBySentence(sentenceId: Int, language: String?): Flow<List<SentenceTranslation>> =
        sentenceTranslationDao.getBySentence(sentenceId, language)

    override fun getByLanguage(language: String): Flow<List<SentenceTranslation>> =
        sentenceTranslationDao.getByLanguage(language)

    override fun search(query: String): Flow<List<SentenceTranslation>> =
        sentenceTranslationDao.search(query)
}
