package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Root

interface RootRepository {
    suspend fun upsert(root: Root)
    suspend fun upsert(roots: List<Root>)
    fun getById(id: String): Flow<Root?>
    fun search(query: String): Flow<List<Root>>
}
