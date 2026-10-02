package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Example

@Dao
interface ExampleDao {
    @Query("SELECT * FROM examples WHERE id = :id")
    fun getById(id: Int): Flow<Example?>

    @Query("SELECT * FROM examples WHERE word = :word")
    fun getByWord(word: String): Flow<List<Example>>

    @Query("SELECT * FROM examples WHERE sentence LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Example>>
}
