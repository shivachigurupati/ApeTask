package com.stc.apetask.data.repository.datasource


import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import io.reactivex.Single

interface FactsDataRemoteDataSource {
    fun factsData(): Single<FactsDataResponse>
}