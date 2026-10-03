package tech.sadique.iqra.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.dao.RootDao
import tech.sadique.iqra.data.local.entity.Root

@Singleton
class RootRepositoryImpl @Inject constructor(
    private val rootDao: RootDao
) : RootRepository {

    override suspend fun upsert(root: Root) = rootDao.upsert(root)

    override suspend fun upsert(roots: List<Root>) = rootDao.upsert(roots)

    override fun getById(id: String): Flow<Root?> = rootDao.getById(id)

    override fun search(query: String): Flow<List<Root>> = rootDao.search(query)
}
