package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE id = :id")
    fun getById(id: String): Flow<Word?>

    @Query("SELECT * FROM words WHERE id LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Word>>
}
