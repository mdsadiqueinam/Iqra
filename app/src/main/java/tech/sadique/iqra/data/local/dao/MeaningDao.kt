package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Meaning

@Dao
interface MeaningDao {
    @Query("SELECT * FROM meanings WHERE id = :id")
    fun getById(id: Int): Flow<Meaning?>

    @Query(
        """
        SELECT * FROM meanings 
        WHERE exampleId = :exampleId 
        AND (:language IS NULL OR language = :language)
        """
    )
    fun getByExample(exampleId: Int, language: String? = null): Flow<List<Meaning>>

    @Query("SELECT * FROM meanings WHERE language = :language")
    fun getByLanguage(language: String): Flow<List<Meaning>>

    @Query("SELECT * FROM meanings WHERE sentence LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Meaning>>
}
