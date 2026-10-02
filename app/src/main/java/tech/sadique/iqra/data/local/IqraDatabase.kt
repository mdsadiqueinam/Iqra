package tech.sadique.iqra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.sadique.iqra.data.local.dao.ExampleDao
import tech.sadique.iqra.data.local.dao.MeaningDao
import tech.sadique.iqra.data.local.dao.RootDao
import tech.sadique.iqra.data.local.dao.WordDao
import tech.sadique.iqra.data.local.entity.Example
import tech.sadique.iqra.data.local.entity.Language
import tech.sadique.iqra.data.local.entity.Meaning
import tech.sadique.iqra.data.local.entity.Root
import tech.sadique.iqra.data.local.entity.Word
import tech.sadique.iqra.data.local.entity.WordCategory
import tech.sadique.iqra.data.local.entity.WordType

@Database(
    entities = [
        Word::class,
        Root::class,
        WordType::class,
        WordCategory::class,
        Language::class,
        Meaning::class,
        Example::class
    ],
    version = 1,
    exportSchema = false
)
abstract class IqraDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun rootDao(): RootDao
    abstract fun exampleDao(): ExampleDao
    abstract fun meaningDao(): MeaningDao

    companion object {
        const val DATABASE_NAME = "iqra_database"
    }
}
