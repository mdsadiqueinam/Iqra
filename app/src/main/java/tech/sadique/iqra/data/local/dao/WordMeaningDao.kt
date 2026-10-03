package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.WordMeaning

@Dao
interface WordMeaningDao {
    @Query("SELECT * FROM word_meanings WHERE id = :id")
    fun getById(id: Int): Flow<WordMeaning?>

    @Query(
        """
        SELECT * FROM word_meanings 
        WHERE wordId = :wordId 
        AND (:language IS NULL OR language = :language)
        """
    )
    fun getByWord(wordId: String, language: String? = null): Flow<List<WordMeaning>>

    @Query("SELECT * FROM word_meanings WHERE language = :language")
    fun getByLanguage(language: String): Flow<List<WordMeaning>>

    @Query("SELECT * FROM word_meanings WHERE text LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<WordMeaning>>
}
