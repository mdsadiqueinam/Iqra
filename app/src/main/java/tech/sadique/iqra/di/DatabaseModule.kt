package tech.sadique.iqra.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.sadique.iqra.data.local.IqraDatabase
import tech.sadique.iqra.data.local.dao.RootDao
import tech.sadique.iqra.data.local.dao.SentenceDao
import tech.sadique.iqra.data.local.dao.SentenceTranslationDao
import tech.sadique.iqra.data.local.dao.WordDao
import tech.sadique.iqra.data.local.dao.WordMeaningDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideIqraDatabase(
        @ApplicationContext context: Context
    ): IqraDatabase = Room.databaseBuilder(
        context,
        IqraDatabase::class.java,
        IqraDatabase.DATABASE_NAME
    ).build()

    @Provides
    fun provideWordDao(database: IqraDatabase): WordDao = database.wordDao()

    @Provides
    fun provideRootDao(database: IqraDatabase): RootDao = database.rootDao()

    @Provides
    fun provideSentenceDao(database: IqraDatabase): SentenceDao = database.sentenceDao()

    @Provides
    fun provideSentenceTranslationDao(
        database: IqraDatabase
    ): SentenceTranslationDao = database.sentenceTranslationDao()

    @Provides
    fun provideWordMeaningDao(
        database: IqraDatabase
    ): WordMeaningDao = database.wordMeaningDao()
}
