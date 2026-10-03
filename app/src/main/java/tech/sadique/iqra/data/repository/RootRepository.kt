package tech.sadique.iqra.data.repository

import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Root

interface RootRepository {
    fun getById(id: String): Flow<Root?>
    fun search(query: String): Flow<List<Root>>
}
