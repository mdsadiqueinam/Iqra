package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Sentence

@Dao
interface SentenceDao {
    @Query("SELECT * FROM sentences WHERE id = :id")
    fun getById(id: Int): Flow<Sentence?>

    @Query("SELECT * FROM sentences WHERE word = :word")
    fun getByWord(word: String): Flow<List<Sentence>>

    @Query("SELECT * FROM sentences WHERE text LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Sentence>>
}
