package com.stc.apetask.data.api

import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface FactsApiService {

    @GET("facts")
    fun loadData(): Single<FactsDataResponse>
}