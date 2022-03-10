package com.stc.apetask.data.models.factsResponse

import com.google.gson.annotations.SerializedName

data class FactsDataResponse(
    @SerializedName("rows")
    val data: List<FactsData>,

    @SerializedName("title")
    val title: String
)