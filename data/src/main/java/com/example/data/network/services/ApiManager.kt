package com.example.data.network.services

import com.example.data.network.api.ApiRequests
import com.example.data.network.common.NetworkResult
import com.example.data.network.extensions.getModel
import com.example.data.network.extensions.map
import com.example.data.network.extensions.safeApiCall
import javax.inject.Inject

class ApiManager @Inject constructor(
    val services: ApiRequests,
) {

    suspend inline fun <reified T> getRequest(
        url: String,
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            services.getRequest(
                url = url,
                headersMap = headersMap,
                queryParamsMap = queryParamsMap
            )
        }.map { response ->
            response?.body()?.getModel()
        }
}