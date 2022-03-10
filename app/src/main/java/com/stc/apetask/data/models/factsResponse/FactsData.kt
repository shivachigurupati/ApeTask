package com.stc.apetask.data.models.factsResponse

import com.google.gson.annotations.SerializedName

data class FactsData(
    @SerializedName("description")
    val description: String,

    @SerializedName("imageHref")
    val imageHref: String,

    @SerializedName("title")
    val title: String
)