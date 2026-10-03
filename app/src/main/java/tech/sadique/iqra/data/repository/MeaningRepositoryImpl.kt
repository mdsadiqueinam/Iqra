package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.MeaningDao
import tech.sadique.iqra.data.local.entity.Meaning

@Singleton
class MeaningRepositoryImpl @Inject constructor(
    private val meaningDao: MeaningDao
) : MeaningRepository {

    override fun getById(id: Int): Flow<Meaning?> = meaningDao.getById(id)

    override fun getByExample(exampleId: Int, language: String?): Flow<List<Meaning>> =
        meaningDao.getByExample(exampleId, language)

    override fun getByLanguage(language: String): Flow<List<Meaning>> =
        meaningDao.getByLanguage(language)

    override fun search(query: String): Flow<List<Meaning>> =
        meaningDao.search(query)
}
