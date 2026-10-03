package tech.sadique.iqra.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.sadique.iqra.data.repository.ExampleRepository
import tech.sadique.iqra.data.repository.ExampleRepositoryImpl
import tech.sadique.iqra.data.repository.MeaningRepository
import tech.sadique.iqra.data.repository.MeaningRepositoryImpl
import tech.sadique.iqra.data.repository.RootRepository
import tech.sadique.iqra.data.repository.RootRepositoryImpl
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
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindMeaningRepository(
        impl: MeaningRepositoryImpl
    ): MeaningRepository
}
