package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE id = :id")
    fun getById(id: String): Flow<Word?>

    @Query(
        """
        SELECT * FROM words 
        WHERE id LIKE '%' || :query || '%' 
        AND (:quranic IS NULL OR isQuranic = :quranic)
        """
    )
    fun search(query: String, quranic: Boolean? = null): Flow<List<Word>>

    @Query(
        """
        SELECT * FROM words 
        WHERE root = :root 
        AND (:quranic IS NULL OR isQuranic = :quranic)
        """
    )
    fun getByRoot(root: String, quranic: Boolean? = null): Flow<List<Word>>

    @Query(
        """
        SELECT * FROM words 
        WHERE type = :type 
        AND (:quranic IS NULL OR isQuranic = :quranic)
        """
    )
    fun getByType(type: String, quranic: Boolean? = null): Flow<List<Word>>

    @Query(
        """
        SELECT * FROM words 
        WHERE category = :category 
        AND (:quranic IS NULL OR isQuranic = :quranic)
        """
    )
    fun getByCategory(category: String, quranic: Boolean? = null): Flow<List<Word>>
}
