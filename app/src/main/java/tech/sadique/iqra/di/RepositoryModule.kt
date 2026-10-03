package tech.sadique.iqra.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.sadique.iqra.data.repository.DailyProgressRepository
import tech.sadique.iqra.data.repository.DailyProgressRepositoryImpl
import tech.sadique.iqra.data.repository.PracticeSessionRepository
import tech.sadique.iqra.data.repository.PracticeSessionRepositoryImpl
import tech.sadique.iqra.data.repository.RootRepository
import tech.sadique.iqra.data.repository.RootRepositoryImpl
import tech.sadique.iqra.data.repository.SentenceRepository
import tech.sadique.iqra.data.repository.SentenceRepositoryImpl
import tech.sadique.iqra.data.repository.SentenceTranslationRepository
import tech.sadique.iqra.data.repository.SentenceTranslationRepositoryImpl
import tech.sadique.iqra.data.repository.WordMeaningRepository
import tech.sadique.iqra.data.repository.WordMeaningRepositoryImpl
import tech.sadique.iqra.data.repository.WordProgressRepository
import tech.sadique.iqra.data.repository.WordProgressRepositoryImpl
import tech.sadique.iqra.data.repository.WordRepository
import tech.sadique.iqra.data.repository.WordRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWordRepository(
        impl: WordRepositoryImpl
    ): WordRepository

    @Binds
    @Singleton
    abstract fun bindRootRepository(
        impl: RootRepositoryImpl
    ): RootRepository

    @Binds
    @Singleton
    abstract fun bindSentenceRepository(
        impl: SentenceRepositoryImpl
    ): SentenceRepository

    @Binds
    @Singleton
    abstract fun bindSentenceTranslationRepository(
        impl: SentenceTranslationRepositoryImpl
    ): SentenceTranslationRepository

    @Binds
    @Singleton
    abstract fun bindWordMeaningRepository(
        impl: WordMeaningRepositoryImpl
    ): WordMeaningRepository

    @Binds
    @Singleton
    abstract fun bindWordProgressRepository(
        impl: WordProgressRepositoryImpl
    ): WordProgressRepository

    @Binds
    @Singleton
    abstract fun bindDailyProgressRepository(
        impl: DailyProgressRepositoryImpl
    ): DailyProgressRepository

    @Binds
    @Singleton
    abstract fun bindPracticeSessionRepository(
        impl: PracticeSessionRepositoryImpl
    ): PracticeSessionRepository
}
