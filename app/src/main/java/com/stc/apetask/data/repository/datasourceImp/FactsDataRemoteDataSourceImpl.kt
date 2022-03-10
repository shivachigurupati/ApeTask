package com.stc.apetask.data.repository.datasourceImp

import com.stc.apetask.data.api.FactsApiService
import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import com.stc.apetask.data.repository.datasource.FactsDataRemoteDataSource
import io.reactivex.Single

class FactsDataRemoteDataSourceImpl(private val factsApiService: FactsApiService) :
    FactsDataRemoteDataSource {

    override fun factsData(): Single<FactsDataResponse> {
        return factsApiService.loadData()
    }
}