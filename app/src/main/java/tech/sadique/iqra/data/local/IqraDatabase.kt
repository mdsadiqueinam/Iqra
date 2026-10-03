package tech.sadique.iqra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.sadique.iqra.data.local.dao.RootDao
import tech.sadique.iqra.data.local.dao.SentenceDao
import tech.sadique.iqra.data.local.dao.SentenceTranslationDao
import tech.sadique.iqra.data.local.dao.WordDao
import tech.sadique.iqra.data.local.dao.WordMeaningDao
import tech.sadique.iqra.data.local.entity.Language
import tech.sadique.iqra.data.local.entity.Root
import tech.sadique.iqra.data.local.entity.Sentence
import tech.sadique.iqra.data.local.entity.SentenceTranslation
import tech.sadique.iqra.data.local.entity.Word
import tech.sadique.iqra.data.local.entity.WordCategory
import tech.sadique.iqra.data.local.entity.WordMeaning
import tech.sadique.iqra.data.local.entity.WordType

@Database(
    entities = [
        Word::class,
        Root::class,
        WordType::class,
        WordCategory::class,
        Language::class,
        Sentence::class,
        SentenceTranslation::class,
        WordMeaning::class
    ],
    version = 1,
    exportSchema = false
)
abstract class IqraDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun rootDao(): RootDao
    abstract fun sentenceDao(): SentenceDao
    abstract fun sentenceTranslationDao(): SentenceTranslationDao
    abstract fun wordMeaningDao(): WordMeaningDao

    companion object {
        const val DATABASE_NAME = "iqra_database"
    }
}
