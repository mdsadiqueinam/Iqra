package tech.sadique.iqra.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.sadique.iqra.data.local.entity.SentenceTranslation

@Dao
interface SentenceTranslationDao {
    @Query("SELECT * FROM sentence_translations WHERE id = :id")
    fun getById(id: Int): Flow<SentenceTranslation?>

    @Query(
        """
        SELECT * FROM sentence_translations 
        WHERE sentenceId = :sentenceId 
        AND (:language IS NULL OR language = :language)
        """
    )
    fun getBySentence(sentenceId: Int, language: String? = null): Flow<List<SentenceTranslation>>

    @Query("SELECT * FROM sentence_translations WHERE language = :language")
    fun getByLanguage(language: String): Flow<List<SentenceTranslation>>

    @Query("SELECT * FROM sentence_translations WHERE text LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<SentenceTranslation>>
}
