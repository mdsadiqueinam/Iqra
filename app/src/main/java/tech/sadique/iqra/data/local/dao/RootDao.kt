package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Root

@Dao
interface RootDao {
    @Query("SELECT * FROM roots WHERE id = :id")
    fun getById(id: String): Flow<Root?>

    @Query("SELECT * FROM roots WHERE id LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Root>>
}
