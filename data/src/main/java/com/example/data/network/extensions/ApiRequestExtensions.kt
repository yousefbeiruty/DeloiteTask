package com.example.data.network.extensions

import com.example.domain.common.ResultWrapper
import com.example.data.network.common.NetworkResult
import com.example.data.network.home.model.ResultResponse
import com.example.domain.model.home.MostPopular

inline fun <DATA, DOMAIN> tryRequest(
    request: () -> NetworkResult<DATA>,
    dataToDomain: (DATA?) -> DOMAIN
) = try {
    when (val response = request()) {
        is NetworkResult.Success -> {
            ResultWrapper.Success(dataToDomain(response.data))
        }
        is NetworkResult.Error -> {
            ResultWrapper.Error(response.error)
        }
    }
} catch (e: Exception) {
    ResultWrapper.Error(e.parseErrorResponse())
}

internal fun List<ResultResponse>.toMostPopular(): List<MostPopular> {

    val mostPopularList = arrayListOf<MostPopular>()

    this.forEach { resultResponse ->
        mostPopularList.add(
            MostPopular(
                resultResponse.title ?: "",
                id = resultResponse.id ?: 0,
                newsImage = if (! resultResponse.mediaResponses.isNullOrEmpty()) resultResponse.mediaResponses[0]?.mediaMetadata?.get(
                    2
                )?.url ?: "" else "",
                publishTime = resultResponse.publishedDate ?: "",
                url = resultResponse.url ?: ""
            )
        )
    }
    return mostPopularList
}