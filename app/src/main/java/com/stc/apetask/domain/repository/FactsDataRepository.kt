package com.stc.apetask.domain.repository

import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import io.reactivex.Single

interface FactsDataRepository {

    fun getFactsData(): Single<FactsDataResponse>

}