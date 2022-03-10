package com.stc.apetask.data.repository

import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import com.stc.apetask.data.repository.datasource.FactsDataRemoteDataSource
import com.stc.apetask.domain.repository.FactsDataRepository
import io.reactivex.Single

class FactsDataRepositoryImpl(
    private val factsDataRemoteDataSource: FactsDataRemoteDataSource
) : FactsDataRepository {
    override fun getFactsData(): Single<FactsDataResponse> {
        return factsDataRemoteDataSource.factsData()
    }
}