package com.stc.apetask.presentation.di.module

import com.stc.apetask.data.repository.FactsDataRepositoryImpl
import com.stc.apetask.data.repository.datasource.FactsDataRemoteDataSource
import com.stc.apetask.domain.repository.FactsDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFactsDataRepository(
        factsDataRemoteDataSource: FactsDataRemoteDataSource
    ): FactsDataRepository {
        return FactsDataRepositoryImpl(factsDataRemoteDataSource)
    }
}