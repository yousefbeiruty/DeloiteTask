package com.example.data.network.home.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MostPopularResponse(

    @SerializedName("copyright")
    val copyright: String? = "",

    @SerializedName("num_results")
    val numResults: Int? = 0,

    @SerializedName("results")
    val resultResponses: List<ResultResponse> = listOf(),

    @SerializedName("status")
    val status: String? = ""
)