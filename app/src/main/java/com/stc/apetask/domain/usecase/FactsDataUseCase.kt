package com.stc.apetask.domain.usecase

import com.stc.apetask.data.models.factsResponse.FactsDataResponse
import com.stc.apetask.domain.repository.FactsDataRepository
import io.reactivex.Single
import javax.inject.Inject

class FactsDataUseCase @Inject constructor(private val factsDataRepository: FactsDataRepository) {

    fun executeFactsData(): Single<FactsDataResponse> {
        return factsDataRepository.getFactsData()
    }
}