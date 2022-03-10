package com.stc.apetask.presentation.di.module

import com.stc.apetask.data.api.FactsApiService
import com.stc.apetask.data.repository.datasource.FactsDataRemoteDataSource
import com.stc.apetask.data.repository.datasourceImp.FactsDataRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSource {

    @Singleton
    @Provides
    fun provideFactsDataRemoteDataSource(factsApiService: FactsApiService): FactsDataRemoteDataSource {
        return FactsDataRemoteDataSourceImpl(factsApiService)
    }
}